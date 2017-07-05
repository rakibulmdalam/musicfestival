-- -----------------------------------------------------
-- Schema MusicFestival
-- -----------------------------------------------------
CREATE DATABASE musicfestival_alternative;

GRANT ALL PRIVILEGES ON DATABASE musicfestival_alternative to practical;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO practical;

--Connect to database
\c musicfestival_alternative;

-- -----------------------------------------------------
-- Table "MusicFestival".User
-- -----------------------------------------------------
CREATE SEQUENCE User_seq;
CREATE TABLE IF NOT EXISTS User (
    id integer NOT NULL DEFAULT NEXTVAL ('User_seq'),
    password VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table "MusicFestival".Provider
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Provider (
    id INT NOT NULL,
    name VARCHAR(200),
    PRIMARY KEY (id),
    CONSTRAINT fk_Provider_User1
        FOREIGN KEY (id)
        REFERENCES User (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE INDEX fk_Provider_User1_idx ON "Provider" (id ASC);

-- -----------------------------------------------------
-- Table "MusicFestival".Band
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Band (
  id INT NOT NULL,
  number_of_members INT NULL,
  genre VARCHAR(45) NULL,
  PRIMARY KEY (id),
    CONSTRAINT fk_Band_Provider1
        FOREIGN KEY (id)
        REFERENCES Provider (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE INDEX fk_Band_Provider1_idx ON Band (id ASC);

-- -----------------------------------------------------
-- Table "MusicFestival".Area
-- -----------------------------------------------------
CREATE SEQUENCE Area_seq;

CREATE TABLE IF NOT EXISTS Area (
  id INT NOT NULL DEFAULT NEXTVAL ('Area_seq'),
  name VARCHAR(250) NULL,
  type VARCHAR(45) NULL,
  PRIMARY KEY (id)
);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Shift
-- -----------------------------------------------------
CREATE SEQUENCE Shift_seq;

CREATE TABLE IF NOT EXISTS Shift (
  id INT NOT NULL DEFAULT NEXTVAL ('Shift_seq'),
  starting_time DATE NULL,
  ending_time DATE NULL,
  area_id INT NOT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Shift_Area1
    FOREIGN KEY (area_id)
    REFERENCES Area (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Shift_Area1_idx ON Shift (area_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Employee
-- -----------------------------------------------------
CREATE TABLE Employee (
    id INT NOT NULL,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    date_of_birth TIMESTAMP,
    role VARCHAR(50),
  PRIMARY KEY (id),
  CONSTRAINT fk_Employee_User1
    FOREIGN KEY (id)
    REFERENCES User (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_Employee_User1_idx ON Employee (id ASC);

-- -----------------------------------------------------
-- Table "MusicFestival".Note
-- -----------------------------------------------------
CREATE SEQUENCE Note_seq;

CREATE TABLE IF NOT EXISTS Note (
  id INT NOT NULL DEFAULT NEXTVAL ('Note_seq'),
  content TEXT NULL,
  time TIMESTAMP NULL,
  area_id INT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Note_Area1
    FOREIGN KEY (area_id)
    REFERENCES Area (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Note_Area1_idx ON Note (area_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Newsletter
-- -----------------------------------------------------
CREATE SEQUENCE Newsletter_seq;

CREATE TABLE IF NOT EXISTS Newsletter (
  id INT NOT NULL DEFAULT NEXTVAL ('Newsletter_seq'),
  name VARCHAR(250) NULL,
  PRIMARY KEY (id)
);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Post
-- -----------------------------------------------------
CREATE SEQUENCE Post_seq;

CREATE TABLE IF NOT EXISTS Post (
  id INT NOT NULL DEFAULT NEXTVAL ('Post_seq'),
  heading VARCHAR(200) NULL,
  content TEXT NULL,
  date TIMESTAMP NULL,
  tags TEXT NULL,
  newsletter_id INT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Post_Newsletter
    FOREIGN KEY (newsletter_id)
    REFERENCES Newsletter (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_Post_Newsletter_idx ON Post (newsletter_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Sponsor
-- -----------------------------------------------------
CREATE SEQUENCE Sponsors_seq;

CREATE TABLE IF NOT EXISTS "Sponsor" (
    id INT NOT NULL,
    paid_money NUMERIC(15,6) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Sponsor_Provider1
        FOREIGN KEY (id)
        REFERENCES Provider (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE INDEX fk_Sponsor_Provider1_idx ON Sponsor (id ASC);
 
-- -----------------------------------------------------
-- Table "MusicFestival".Product
-- -----------------------------------------------------
CREATE SEQUENCE Product_seq;

CREATE TABLE IF NOT EXISTS Product (
  id INT NOT NULL DEFAULT NEXTVAL ('Product_seq'),
  name VARCHAR(200) NULL,
  type VARCHAR(45) NULL,
  quantity INT NULL,
  price NUMERIC(15,6) NULL,
  provider_id INT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Product_Provider1
    FOREIGN KEY (provider_id)
    REFERENCES Provider (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_Product_Band1_idx ON Product (provider_id ASC);
 
-- -----------------------------------------------------
-- Table "MusicFestival".Pressinfo
-- -----------------------------------------------------
CREATE SEQUENCE Pressinfo_seq;

CREATE TABLE IF NOT EXISTS Pressinfo (
  id INT NOT NULL DEFAULT NEXTVAL ('Pressinfo_seq'),
  date TIMESTAMP NULL,
  text TEXT NULL,
  band_id INT NOT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Pressinfo_Band1
    FOREIGN KEY (band_id)
    REFERENCES "Band" (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Pressinfo_Band1_idx ON Pressinfo (band_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Advertisement
-- -----------------------------------------------------
CREATE SEQUENCE Advertisement_seq;

CREATE TABLE IF NOT EXISTS Advertisement (
  id INT NOT NULL DEFAULT NEXTVAL ('Advertisement_seq'),
  cost INT NOT NULL,
  type VARCHAR(45) NULL,
  location VARCHAR(45) NULL,
  sponsor_id INT NOT NULL,
  area_id INT NOT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Advertisement_Sponsor1
    FOREIGN KEY (sponsor_id)
    REFERENCES Sponsor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Advertisement_Area1
    FOREIGN KEY (area_id)
    REFERENCES "Area" (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Advertisement_Sponsor1_idx ON Advertisement (sponsor_id ASC);
CREATE INDEX fk_Advertisement_Area1_idx ON Advertisement (area_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Application
-- -----------------------------------------------------
CREATE SEQUENCE Application_seq;

CREATE TABLE IF NOT EXISTS Application (
  id INT NOT NULL DEFAULT NEXTVAL ('Application_seq'),
  status VARCHAR(20) NULL,
  provider_id INT NULL,
  approved_by INT NULL,
  approval_time TIMESTAMP,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Application_Provider1
    FOREIGN KEY (provider_id)
    REFERENCES Provider (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT fk_Application_Employee1
    FOREIGN KEY (approved_by)
    REFERENCES Employee (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_Application_Provider1_idx ON Application (provider_id ASC);
CREATE INDEX fk_Application_Employee1_idx ON Application (approved_by ASC);
 
-- -----------------------------------------------------
-- Table "MusicFestival".Stage
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Stage (
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (name)
);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Schedule
-- -----------------------------------------------------
CREATE SEQUENCE Schedule_seq;

CREATE TABLE IF NOT EXISTS Schedule (
  id INT NOT NULL DEFAULT NEXTVAL ('Schedule_seq'),
  time_build_up TIMESTAMP NOT NULL,
  time_start_playing TIMESTAMP NOT NULL,
  time_finish_playing TIMESTAMP NOT NULL,
  time_leave_stage TIMESTAMP(0) NOT NULL,
  stage_name VARCHAR(45) NULL,
  band_id INT NULL,
  PRIMARY KEY (ID)
 ,
  CONSTRAINT fk_Schedule_Stage1
    FOREIGN KEY (stage_name)
    REFERENCES Stage (name)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Schedule_Band1
    FOREIGN KEY (band_id)
    REFERENCES Band (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Schedule_Stage1_idx ON Schedule (stage_name ASC);
CREATE INDEX fk_Schedule_Band1_idx ON Schedule (band_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Song
-- -----------------------------------------------------
CREATE SEQUENCE Song_seq;

CREATE TABLE IF NOT EXISTS Song (
  id INT NOT NULL DEFAULT NEXTVAL ('Song_seq'),
  title VARCHAR(100) NOT NULL,
  publisher VARCHAR(200) NOT NULL,
  band_id INT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Song_Band1
    FOREIGN KEY (band_id)
    REFERENCES Band (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Song_Band1_idx ON Song (band_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Shop
-- -----------------------------------------------------
CREATE SEQUENCE Shop_seq;

CREATE TABLE IF NOT EXISTS Shop (
  id INT NOT NULL DEFAULT NEXTVAL ('Shop_seq'),
  name VARCHAR(200) NOT NULL,
  sponsor_id INT NOT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Shop_Sponsor1
    FOREIGN KEY (sponsor_id)
    REFERENCES Sponsor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Shop_Sponsor1_idx ON Shop (sponsor_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".TicketClass
-- -----------------------------------------------------
CREATE SEQUENCE Ticketclass_seq;

CREATE TABLE IF NOT EXISTS Ticketclass (
  id INT NOT NULL DEFAULT NEXTVAL ('Ticketclass_seq'),
  price NUMERIC(15,6) NULL,
  description TEXT NULL,
  PRIMARY KEY (id)
);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Visitor
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Visitor (
  id INT NOT NULL,
  last_name VARCHAR(100) NULL,
  first_name VARCHAR(100) NULL,
  date_of_birth TIMESTAMP NULL,
  balance NUMERIC(15,6) NULL,
  ticketclass_id INT NOT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Visitor_User1
    FOREIGN KEY (id)
    REFERENCES User (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Visitor_TicketClass1
    FOREIGN KEY (ticketclass_id)
    REFERENCES Ticketclass (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_Visitor_User1_idx ON Visitor (id ASC);
CREATE INDEX fk_Visitor_TicketClass1_idx ON Visitor (ticketclass_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Wristband
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Wristband (
  rfid INT NOT NULL,
  color VARCHAR(45) NULL,
  activation TIMESTAMP NULL,
  deactivation TIMESTAMP NULL,
  status BOOLEAN NOT NULL DEFAULT false,
  visitor_id INT NOT NULL,
  PRIMARY KEY (rfid)
 ,
  CONSTRAINT fk_Wristband_Visitor1
    FOREIGN KEY (visitor_id)
    REFERENCES Visitor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Wristband_Visitor1_idx ON Wristband (visitor_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Newsletter_Subscription
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Newsletter_Subscription (
  visitor_id INT NOT NULL,
  newsletter_id INT NOT NULL,
  PRIMARY KEY (visitor_id, newsletter_id)
 ,
  CONSTRAINT fk_Visitor_has_Newsletter_Visitor1
    FOREIGN KEY (visitor_id)
    REFERENCES Visitor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Visitor_has_Newsletter_Newsletter1
    FOREIGN KEY (newsletter_id)
    REFERENCES Newsletter (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Visitor_has_Newsletter_Newsletter1_idx ON Newsletter_Subscription (newsletter_id ASC);
CREATE INDEX fk_Visitor_has_Newsletter_Visitor1_idx ON Newsletter_Subscription (visitor_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Stage_Employee
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Stage_Employee (
  employee_id INT NOT NULL,
  stage_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (employee_id, stage_name)
 ,
  CONSTRAINT fk_Employee_has_Stage_Employee1
    FOREIGN KEY (employee_id)
    REFERENCES Employee (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Employee_has_Stage_Stage1
    FOREIGN KEY (stage_name)
    REFERENCES Stage (name)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Employee_has_Stage_Stage1_idx ON Stage_Employee (stage_name ASC);
CREATE INDEX fk_Employee_has_Stage_Employee1_idx ON Stage_Employee (employee_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Ticketclass_Permission
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Ticketclass_Permission (
  area_id INT NOT NULL,
  ticketclass_id INT NOT NULL,
  PRIMARY KEY (area_id, ticketclass_id)
 ,
  CONSTRAINT fk_Area_has_TicketClass_Area1
    FOREIGN KEY (area_id)
    REFERENCES Area (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Area_has_TicketClass_TicketClass1
    FOREIGN KEY (ticketclass_id)
    REFERENCES Ticketclass (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Area_has_TicketClass_TicketClass1_idx ON Ticketclass_Permission (ticketclass_id ASC);
CREATE INDEX fk_Area_has_TicketClass_Area1_idx ON Ticketclass_Permission (area_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Visitor_Access_List
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Visitor_Access_List (
  area_id INT NOT NULL,
  visitor_id INT NOT NULL,
  PRIMARY KEY (area_id, visitor_id)
 ,
  CONSTRAINT fk_Area_has_Visitor_Area1
    FOREIGN KEY (area_id)
    REFERENCES Area (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Area_has_Visitor_Visitor1
    FOREIGN KEY (visitor_id)
    REFERENCES Visitor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Area_has_Visitor_Visitor1_idx ON Visitor_Access_List (visitor_id ASC);
CREATE INDEX fk_Area_has_Visitor_Area1_idx ON Visitor_Access_List (area_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Visitor_Schedule
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Visitor_Schedule (
  visitor_id INT NOT NULL,
  schedule_id INT NOT NULL,
  
  PRIMARY KEY (visitor_id, schedule_id),
  CONSTRAINT fk_Visitor_has_Schedule_Visitor1
    FOREIGN KEY (visitor_id)
    REFERENCES Visitor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Visitor_has_Schedule_Schedule1
    FOREIGN KEY (schedule_id)
    REFERENCES Schedule (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Visitor_has_Schedule_Schedule1_idx ON Visitor_Schedule (schedule_id ASC);
CREATE INDEX fk_Visitor_has_Schedule_Visitor1_idx ON Visitor_Schedule (visitor_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival"."Note_Notification"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Note_Notification (
  employee_id INT NOT NULL,
  note_id INT NOT NULL,
  seen BOOLEAN NULL,
  PRIMARY KEY (employee_id, note_id)
 ,
  CONSTRAINT fk_Employee_has_Note_Employee1
    FOREIGN KEY (employee_id)
    REFERENCES Employee (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Employee_has_Note_Note1
    FOREIGN KEY (note_id)
    REFERENCES Note (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Employee_has_Note_Note1_idx ON Note_Notification (note_id ASC);
CREATE INDEX fk_Employee_has_Note_Employee1_idx ON Note_Notification (employee_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Sell
-- -----------------------------------------------------

CREATE SEQUENCE Sell_seq;

CREATE TABLE IF NOT EXISTS Sell (
  id INT NOT NULL DEFAULT NEXTVAL ('Sell_seq'),
  product_id INT NOT NULL,
  shop_id INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Product_has_Shop_Product1
    FOREIGN KEY (product_id)
    REFERENCES Product (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Product_has_Shop_Shop1
    FOREIGN KEY (shop_id)
    REFERENCES Shop (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Product_has_Shop_Shop1_idx ON Sell (shop_id ASC);
CREATE INDEX fk_Product_has_Shop_Product1_idx ON Sell (product_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Visitor_Access_Log
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Visitor_Access_Log (
  area_id INT NOT NULL,
  visitor_id INT NOT NULL,
  date TIMESTAMP NULL,
  PRIMARY KEY (area_id, visitor_id)
 ,
  CONSTRAINT fk_Area_has_Visitor1_Area1
    FOREIGN KEY (area_id)
    REFERENCES Area (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Area_has_Visitor1_Visitor1
    FOREIGN KEY (visitor_id)
    REFERENCES Visitor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Area_has_Visitor1_Visitor1_idx ON Visitor_Access_Log (visitor_id ASC);
CREATE INDEX fk_Area_has_Visitor1_Area1_idx ON Visitor_Access_Log (area_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Purchase
-- -----------------------------------------------------
CREATE SEQUENCE Purchase_seq;

CREATE TABLE IF NOT EXISTS Purchase (
  id INT NOT NULL DEFAULT NEXTVAL ('Purchase_seq'),
  sell_id INT NOT NULL,
  visitor_id INT NOT NULL,
  date TIMESTAMP NOT NULL
 ,
  PRIMARY KEY (ID),
  CONSTRAINT fk_Sell_has_Visitor_Sell1
    FOREIGN KEY (sell_id)
    REFERENCES Sell (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Sell_has_Visitor_Visitor1
    FOREIGN KEY (visitor_id)
    REFERENCES Visitor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Sell_has_Visitor_Visitor1_idx ON Purchase (visitor_id ASC);
CREATE INDEX fk_Sell_has_Visitor_Sell1_idx ON Purchase (sell_id ASC);
 
 
-- -----------------------------------------------------
-- Table "MusicFestival".Band_Employees_Interaction
-- -----------------------------------------------------
CREATE SEQUENCE Band_Employees_Interaction_seq;

CREATE TABLE IF NOT EXISTS Band_Employees_Interaction (
  id INT NOT NULL DEFAULT NEXTVAL ('Band_Employees_Interaction_seq'),
  employee_id INT NULL,
  stage_name VARCHAR(45) NULL,
  band_id INT NULL,
  date TIMESTAMP NULL,
  description TEXT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_StageEmployee_has_Band_StageEmployee1
    FOREIGN KEY (employee_id , stage_name)
    REFERENCES Stage_Employee (employee_id , stage_name)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_StageEmployee_has_Band_Band1
    FOREIGN KEY (band_id)
    REFERENCES "Band" (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_StageEmployee_has_Band_Band1_idx ON Band_Employees_Interaction (band_id ASC);
CREATE INDEX fk_StageEmployee_has_Band_StageEmployee1_idx ON Band_Employees_Interaction (employee_id ASC, stage_name ASC);

-- -----------------------------------------------------
-- Table "MusicFestival".Employee_Shift
-- -----------------------------------------------------
 
CREATE TABLE IF NOT EXISTS Employee_Shift (
  shift_id INT NOT NULL,
  employee_id INT NOT NULL,
  PRIMARY KEY (shift_id, employee_id)
 ,
  CONSTRAINT fk_Shift_has_Employee_Shift1
    FOREIGN KEY (shift_id)
    REFERENCES Shift (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Shift_has_Employee_Employee1
    FOREIGN KEY (employee_id)
    REFERENCES Employee (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;
 
CREATE INDEX fk_Shift_has_Employee_Employee1_idx ON Employee_Shift (employee_id ASC);
CREATE INDEX fk_Shift_has_Employee_Shift1_idx ON Employee_Shift (shift_id ASC);
