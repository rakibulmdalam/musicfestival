-- -----------------------------------------------------
-- Schema MusicFestival
-- -----------------------------------------------------
CREATE DATABASE musicfestival;

-- GRANT ALL PRIVILEGES ON DATABASE musicfestival_alternative to practical;
-- GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO practical;

-- Connect to database
\c musicfestival;

-- -----------------------------------------------------
-- Table MusicFestival.FestivalUser
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS FestivalUser (
    id SERIAL NOT NULL ,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    phone VARCHAR(20),
    picture VARCHAR(200),
    role VARCHAR(50),
    PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table MusicFestival.Provider
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Provider (
    id INT NOT NULL,
    name VARCHAR(200),
    PRIMARY KEY (id),
    CONSTRAINT fk_Provider_User FOREIGN KEY (id) REFERENCES FestivalUser (id)
);

CREATE INDEX fk_Provider_User_idx ON Provider (id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Band
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Band (
    id INT NOT NULL,
    number_of_members INT NULL,
    genre VARCHAR(45) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Band_Provider FOREIGN KEY (id) REFERENCES Provider (id)
);

CREATE INDEX fk_Band_Provider_idx ON Band (id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Area
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Area (
    id SERIAL NOT NULL,
    name VARCHAR(250) NULL,
    type VARCHAR(45) NULL,
    PRIMARY KEY (id)
);
 
-- -----------------------------------------------------
-- Table MusicFestival.Shift
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Shift (
    id SERIAL NOT NULL ,
    starting_time TIMESTAMP NULL,
    ending_time TIMESTAMP NULL,
    area_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Shift_Area FOREIGN KEY (area_id) REFERENCES Area (id)
);

CREATE INDEX fk_Shift_Area_idx ON Shift (area_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Employee
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Employee (
    id INT NOT NULL,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    date_of_birth DATE,
    role VARCHAR(50),
    PRIMARY KEY (id),
    CONSTRAINT fk_Employee_User FOREIGN KEY (id) REFERENCES FestivalUser (id)   
);

CREATE INDEX fk_Employee_User_idx ON Employee (id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Note
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Note (
    id SERIAL NOT NULL ,
    content TEXT NULL,
    creation_time TIMESTAMP NULL DEFAULT now(),
    area_id INT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Note_Area FOREIGN KEY (area_id) REFERENCES Area (id)   
);

CREATE INDEX fk_Note_Area_idx ON Note (area_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Newsletter
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Newsletter (
    id SERIAL NOT NULL,
    name VARCHAR(250) NULL,
    PRIMARY KEY (id)
);
 
-- -----------------------------------------------------
-- Table MusicFestival.Post
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Post (
    id SERIAL NOT NULL ,
    heading VARCHAR(200) NULL,
    content VARCHAR(200) NULL,
    publish_date TIMESTAMP NULL DEFAULT now(),
    tags VARCHAR(200) NULL,
    newsletter_id INT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Post_Newsletter FOREIGN KEY (newsletter_id) REFERENCES Newsletter (id)
);

CREATE INDEX fk_Post_Newsletter_idx ON Post (newsletter_id ASC);
 
-- -----------------------------------------------------
-- Table MusicFestival.Sponsor
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Sponsor (
    id INT NOT NULL,
    paid_money NUMERIC(15,2) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Sponsor_Provider FOREIGN KEY (id) REFERENCES Provider (id)
);

CREATE INDEX fk_Sponsor_Provider_idx ON Sponsor (id ASC);
 
-- -----------------------------------------------------
-- Table MusicFestival.Product
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Product (
    id SERIAL NOT NULL ,
    name VARCHAR(200) NULL,
    type VARCHAR(45) NULL,
    price NUMERIC(15,2) NULL,
    provider_id INT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Product_Provider FOREIGN KEY (provider_id) REFERENCES Provider (id)
);

CREATE INDEX fk_Product_Provider_idx ON Product (provider_id ASC);
 
-- -----------------------------------------------------
-- Table MusicFestival.Pressinfo
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Pressinfo (
    id SERIAL NOT NULL,
    publish_date TIMESTAMP NULL DEFAULT now(),
    text VARCHAR(200) NULL,
    band_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Pressinfo_Band FOREIGN KEY (band_id) REFERENCES Band (id)
);

CREATE INDEX fk_Pressinfo_Band_idx ON Pressinfo (band_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Advertisement
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Advertisement (
    id SERIAL NOT NULL,
    cost NUMERIC(15,2) NOT NULL,
    type VARCHAR(45) NULL,
    sponsor_id INT NOT NULL,
    area_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Advertisement_Sponsor FOREIGN KEY (sponsor_id) REFERENCES Sponsor (id),
    CONSTRAINT fk_Advertisement_Area FOREIGN KEY (area_id) REFERENCES Area (id)
);

CREATE INDEX fk_Advertisement_Sponsor_idx ON Advertisement (sponsor_id ASC);
CREATE INDEX fk_Advertisement_Area_idx ON Advertisement (area_id ASC);
 
-- -----------------------------------------------------
-- Table MusicFestival.Application
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Application (
    id SERIAL NOT NULL,
    status VARCHAR(20) NULL,
    provider_id INT NULL,
    approved_by INT NULL,
    approval_date TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_Application_Provider FOREIGN KEY (provider_id) REFERENCES Provider (id),
    CONSTRAINT fk_Application_Employee FOREIGN KEY (approved_by) REFERENCES Employee (id)
);

CREATE INDEX fk_Application_Provider_idx ON Application (provider_id ASC);
CREATE INDEX fk_Application_Employee_idx ON Application (approved_by ASC);
 
-- -----------------------------------------------------
-- Table MusicFestival.Stage
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Stage (
    name VARCHAR(45) NOT NULL,
    area_id INT NOT NULL,
    PRIMARY KEY (name),
    CONSTRAINT fk_Stage_Area FOREIGN KEY (area_id) REFERENCES Area (id)
);
 
CREATE INDEX fk_Stage_Area_idx ON Stage (area_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Schedule
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Schedule (
    id SERIAL NOT NULL,
    time_build_up TIMESTAMP NOT NULL,
    time_start_playing TIMESTAMP NOT NULL,
    time_finish_playing TIMESTAMP NOT NULL,
    time_leave_stage TIMESTAMP NOT NULL,
    stage_name VARCHAR(45) NULL,
    band_id INT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT fk_Schedule_Stage FOREIGN KEY (stage_name) REFERENCES Stage (name),
    CONSTRAINT fk_Schedule_Band FOREIGN KEY (band_id) REFERENCES Band (id)
);

CREATE INDEX fk_Schedule_Stage_idx ON Schedule (stage_name ASC);
CREATE INDEX fk_Schedule_Band_idx ON Schedule (band_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Song
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Song (
    id SERIAL NOT NULL,
    title VARCHAR(100) NOT NULL,
    band_id INT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Song_Band FOREIGN KEY (band_id) REFERENCES Band (id)   
);

CREATE INDEX fk_Song_Band_idx ON Song (band_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Shop
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Shop (
    id SERIAL NOT NULL,
    name VARCHAR(200) NOT NULL,
    sponsor_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Shop_Sponsor FOREIGN KEY (sponsor_id) REFERENCES Sponsor (id)
);

CREATE INDEX fk_Shop_Sponsor_idx ON Shop (sponsor_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.TicketClass
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Ticketclass (
    id SERIAL NOT NULL ,
    price NUMERIC(15,2) NULL,
    description TEXT NULL,
    PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table MusicFestival.Visitor
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Visitor (
    id INT NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    balance NUMERIC(15,2) DEFAULT 0,
    ticketclass_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Visitor_User FOREIGN KEY (id) REFERENCES FestivalUser (id),
    CONSTRAINT fk_Visitor_TicketClass FOREIGN KEY (ticketclass_id) REFERENCES Ticketclass (id)
);

CREATE INDEX fk_Visitor_User_idx ON Visitor (id ASC);
CREATE INDEX fk_Visitor_TicketClass_idx ON Visitor (ticketclass_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Wristband
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Wristband (
    rfid INT NOT NULL,
    activation_time TIMESTAMP,
    deactivation_time TIMESTAMP,
    visitor_id INT NOT NULL,
    PRIMARY KEY (rfid),
    CONSTRAINT fk_Wristband_Visitor FOREIGN KEY (visitor_id) REFERENCES Visitor (id)
);

CREATE INDEX fk_Wristband_Visitor_idx ON Wristband (visitor_id ASC);
 
-- -----------------------------------------------------
-- Table MusicFestival.Newsletter_Subscription
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Newsletter_Subscription (
    visitor_id INT NOT NULL,
    newsletter_id INT NOT NULL,
    PRIMARY KEY (visitor_id, newsletter_id),
    CONSTRAINT fk_Subscription_Visitor FOREIGN KEY (visitor_id) REFERENCES Visitor (id),
    CONSTRAINT fk_Subscription_Newsletter FOREIGN KEY (newsletter_id) REFERENCES Newsletter (id)
);

CREATE INDEX fk_Subscription_Newsletter_idx ON Newsletter_Subscription (newsletter_id ASC);
CREATE INDEX fk_Subscription_Visitor_idx ON Newsletter_Subscription (visitor_id ASC);
 
 
-- -----------------------------------------------------
-- Table MusicFestival.Stage_Employee
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Stage_Employee (
    employee_id INT NOT NULL,
    stage_name VARCHAR(45) NOT NULL,
    PRIMARY KEY (employee_id, stage_name),
    CONSTRAINT fk_Stage_Employee FOREIGN KEY (employee_id) REFERENCES Employee (id),
    CONSTRAINT fk_Stage_Stage FOREIGN KEY (stage_name) REFERENCES Stage (name)
);

CREATE INDEX fk_Stage_Stage_idx ON Stage_Employee (stage_name ASC);
CREATE INDEX fk_Stage_Employee_idx ON Stage_Employee (employee_id ASC);
 
-- -----------------------------------------------------
-- Table MusicFestival.Ticketclass_Permission
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Ticketclass_Permission (
    area_id INT NOT NULL,
    ticketclass_id INT NOT NULL,
    PRIMARY KEY (area_id, ticketclass_id),
    CONSTRAINT fk_TicketClassPermission_Area FOREIGN KEY (area_id) REFERENCES Area (id),
    CONSTRAINT fk_TicketClassPermission_TicketClass FOREIGN KEY (ticketclass_id) REFERENCES Ticketclass (id)
);

CREATE INDEX fk_TicketClassPermission_TicketClass_idx ON Ticketclass_Permission (ticketclass_id ASC);
CREATE INDEX fk_TicketClassPermission_Area_idx ON Ticketclass_Permission (area_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Visitor_Access_List
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Visitor_Access_List (
    area_id INT NOT NULL,
    visitor_id INT NOT NULL,
    PRIMARY KEY (area_id, visitor_id),
    CONSTRAINT fk_VisitorAccess_Area FOREIGN KEY (area_id) REFERENCES Area (id),
    CONSTRAINT fk_VisitorAccess_Visitor FOREIGN KEY (visitor_id) REFERENCES Visitor (id)
);

CREATE INDEX fk_VisitorPermission_Visitor_idx ON Visitor_Access_List (visitor_id ASC);
CREATE INDEX fk_VisitorPermission_Area_idx ON Visitor_Access_List (area_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Visitor_Schedule
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Visitor_Schedule (
    visitor_id INT NOT NULL,
    schedule_id INT NOT NULL,
    PRIMARY KEY (visitor_id, schedule_id),
    CONSTRAINT fk_Visitor_has_Schedule_Visitor FOREIGN KEY (visitor_id) REFERENCES Visitor (id),
    CONSTRAINT fk_Visitor_has_Schedule_Schedule FOREIGN KEY (schedule_id) REFERENCES Schedule (id)
);

CREATE INDEX fk_Visitor_has_Schedule_Schedule_idx ON Visitor_Schedule (schedule_id ASC);
CREATE INDEX fk_Visitor_has_Schedule_Visitor_idx ON Visitor_Schedule (visitor_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Note_Notification
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Note_Notification (
    employee_id INT NOT NULL,
    note_id INT NOT NULL,
    time_seen TIMESTAMP,
    PRIMARY KEY (employee_id, note_id),
    CONSTRAINT fk_NoteNotification_Employee FOREIGN KEY (employee_id) REFERENCES Employee (id),
    CONSTRAINT fk_NoteNotification_Note FOREIGN KEY (note_id) REFERENCES Note (id)
);

CREATE INDEX fk_NoteNotification_Note_idx ON Note_Notification (note_id ASC);
CREATE INDEX fk_NoteNotification_Employee_idx ON Note_Notification (employee_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Sell
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Sell (
    id SERIAL NOT NULL,
    product_id INT NOT NULL,
    shop_id INT NOT NULL,
    quantity_available INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Sell_Product FOREIGN KEY (product_id) REFERENCES Product (id),
    CONSTRAINT fk_Sell_Shop FOREIGN KEY (shop_id) REFERENCES Shop (id)
);

CREATE INDEX fk_Sell_Product_idx ON Sell (shop_id ASC);
CREATE INDEX fk_Sell_Shop_idx ON Sell (product_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Visitor_Access_Log
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Visitor_Access_Log (
    area_id INT NOT NULL,
    visitor_id INT NOT NULL,
    access_time TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (area_id, visitor_id, access_time),
    CONSTRAINT fk_VisitorAccess_Area FOREIGN KEY (area_id) REFERENCES Area (id),
    CONSTRAINT fk_VisitorAccess_Visitor FOREIGN KEY (visitor_id) REFERENCES Visitor (id)
);

CREATE INDEX fk_VisitorAccess_Visitor_idx ON Visitor_Access_Log (visitor_id ASC);
CREATE INDEX fk_VisitorAccess_Area_idx ON Visitor_Access_Log (area_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Purchase
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Purchase (
    id SERIAL NOT NULL ,
    sell_id INT NOT NULL,
    visitor_id INT NOT NULL,
    purchase_date TIMESTAMP NOT NULL DEFAULT now(),
    quantity INT DEFAULT 1,
    PRIMARY KEY (sell_id, visitor_id, purchase_date),
    CONSTRAINT fk_Purchase_Sell FOREIGN KEY (sell_id) REFERENCES Sell (id),
    CONSTRAINT fk_Purchase_Visitor FOREIGN KEY (visitor_id) REFERENCES Visitor (id)
);

CREATE INDEX fk_Purchase_Visitor_idx ON Purchase (visitor_id ASC);
CREATE INDEX fk_Purchase_Sell_idx ON Purchase (sell_id ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Band_Employee_Interaction
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Band_Employee_Interaction (
    employee_id INT NULL,
    stage_name VARCHAR(45) NULL,
    band_id INT NULL,
    interaction_date TIMESTAMP NULL DEFAULT now(),
    description TEXT NULL,
    PRIMARY KEY (employee_id, band_id, interaction_date),
    CONSTRAINT fk_BandInteraction_StageEmployee FOREIGN KEY (employee_id, stage_name) REFERENCES Stage_Employee (employee_id, stage_name),
    CONSTRAINT fk_BandInteraction_Band FOREIGN KEY (band_id) REFERENCES Band (id)
);

CREATE INDEX fk_BandInteraction_Band_idx ON Band_Employee_Interaction (band_id ASC);
CREATE INDEX fk_BandInteraction_StageEmployee_idx ON Band_Employee_Interaction (employee_id ASC, stage_name ASC);

-- -----------------------------------------------------
-- Table MusicFestival.Employee_Shift
-- -----------------------------------------------------
 
CREATE TABLE IF NOT EXISTS Employee_Shift (
    shift_id INT NOT NULL,
    employee_id INT NOT NULL,
    PRIMARY KEY (shift_id, employee_id),
    CONSTRAINT fk_EmployeeShift_Shift FOREIGN KEY (shift_id) REFERENCES Shift (id),
    CONSTRAINT fk_EmployeeShift_Employee FOREIGN KEY (employee_id) REFERENCES Employee (id)
);
 
CREATE INDEX fk_EmployeeShift_Employee_idx ON Employee_Shift (employee_id ASC);
CREATE INDEX fk_EmployeeShift_Shift_idx ON Employee_Shift (shift_id ASC);
