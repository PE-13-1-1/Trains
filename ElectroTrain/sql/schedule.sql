/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     23.09.2014 22:37:12                          */
/*==============================================================*/
use KharkovTrain
go

if exists (select 1
          from sysobjects
          where id = object_id('"CLR Trigger_direction"')
          and type = 'TR')
   drop trigger "CLR Trigger_direction"
go

if exists (select 1
          from sysobjects
          where id = object_id('"CLR Trigger_train"')
          and type = 'TR')
   drop trigger "CLR Trigger_train"
go

if exists (select 1
          from sysobjects
          where id = object_id('ti_train')
          and type = 'TR')
   drop trigger ti_train
go

if exists (select 1
          from sysobjects
          where id = object_id('tu_train')
          and type = 'TR')
   drop trigger tu_train
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Station') and o.name = 'FK_STATION_THE STATI_DIRECTIO')
alter table Station
   drop constraint "FK_STATION_THE STATI_DIRECTIO"
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Stop') and o.name = 'FK_STOP_REFERENCE_TRAINSCH')
alter table Stop
   drop constraint FK_STOP_REFERENCE_TRAINSCH
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Stop') and o.name = 'FK_STOP_REFERENCE_STATION')
alter table Stop
   drop constraint FK_STOP_REFERENCE_STATION
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Train') and o.name = 'FK_TRAIN_REFERENCE_TRAINSCH')
alter table Train
   drop constraint FK_TRAIN_REFERENCE_TRAINSCH
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Direction')
            and   type = 'U')
   drop table Direction
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Station')
            and   type = 'U')
   drop table Station
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Stop')
            and   type = 'U')
   drop table Stop
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Train')
            and   type = 'U')
   drop table Train
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TrainSchedule')
            and   type = 'U')
   drop table TrainSchedule
go

/*==============================================================*/
/* Table: Direction                                             */
/*==============================================================*/
create table Direction (
   directionId          int                  not null,
   directionTitle       varchar(Max)         not null,
   constraint PK_DIRECTION primary key (directionId)
)
go

/*==============================================================*/
/* Table: Station                                               */
/*==============================================================*/
create table Station (
   stationId            int                  not null,
   stationName          nvarchar(Max)        not null,
   directionId          int                  not null,
   constraint PK_STATION primary key (stationId)
)
go

/*==============================================================*/
/* Table: Stop                                                  */
/*==============================================================*/
create table Stop (
   stationId            int                  null,
   timeArrival          date                 null,
   timeDeparture        date                 null,
   staying              int                  null,
   stopId               int                  not null,
   scheduleId           int                  null,
   constraint PK_STOP primary key (stopId)
)
go

/*==============================================================*/
/* Table: Train                                                 */
/*==============================================================*/
create table Train (
   trainId              int                  not null,
   startPoint           nvarchar(Max)        not null,
   finalPoint           nvarchar(Max)        not null,
   status               tinyint              not null,
   directionId          int                  not null,
   trainNumber          int                  not null,
   scheduleId           int                  null,
   constraint PK_TRAIN primary key (trainId)
)
go

/*==============================================================*/
/* Table: TrainSchedule                                         */
/*==============================================================*/
create table TrainSchedule (
   scheduleId           int                  not null,
   stopId               int                  null,
   constraint PK_TRAINSCHEDULE primary key (scheduleId)
)
go

alter table Station
   add constraint "FK_STATION_THE STATI_DIRECTIO" foreign key (directionId)
      references Direction (directionId)
go

alter table Stop
   add constraint FK_STOP_REFERENCE_TRAINSCH foreign key (scheduleId)
      references TrainSchedule (scheduleId)
go

alter table Stop
   add constraint FK_STOP_REFERENCE_STATION foreign key (stationId)
      references Station (stationId)
go

alter table Train
   add constraint FK_TRAIN_REFERENCE_TRAINSCH foreign key (scheduleId)
      references TrainSchedule (scheduleId)
go


create trigger "CLR Trigger_direction" on Direction  insert as
external name %Assembly.GeneratedName%.
go


create trigger "CLR Trigger_train" on Train  insert as
external name %Assembly.GeneratedName%.
go


create trigger ti_train on Train for insert as
begin
    declare
       @numrows  int,
       @numnull  int,
       @errno    int,
       @errmsg   varchar(255)

    select  @numrows = @@rowcount
    if @numrows = 0
       return

    /*  Parent "Direction" must exist when inserting a child in "Train"  */
    if update(directionId)
    begin
       if (select count(*)
           from   Direction t1, inserted t2
           where  t1.directionId = t2.directionId) != @numrows
          begin
             select @errno  = 50002,
                    @errmsg = 'Parent does not exist in "Direction". Cannot create child in "Train".'
             goto error
          end
    end

    return

/*  Errors handling  */
error:
    raiserror @errno @errmsg
    rollback  transaction
end
go


create trigger tu_train on Train for update as
begin
   declare
      @numrows  int,
      @numnull  int,
      @errno    int,
      @errmsg   varchar(255)

      select  @numrows = @@rowcount
      if @numrows = 0
         return

      /*  Parent "Direction" must exist when updating a child in "Train"  */
      if update(directionId)
      begin
         if (select count(*)
             from   Direction t1, inserted t2
             where  t1.directionId = t2.directionId) != @numrows
            begin
               select @errno  = 50003,
                      @errmsg = 'Direction" does not exist. Cannot modify child in "Train".'
               goto error
            end
      end

      return

/*  Errors handling  */
error:
    raiserror @errno @errmsg
    rollback  transaction
end
go

