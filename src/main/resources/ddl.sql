-- Database Railway

USE railway;

-- Database Local

DROP DATABASE IF EXISTS surveymanager;

CREATE DATABASE surveymanager;

USE surveymanager;

-- Register and login

CREATE TABLE users (
    id INT AUTO_INCREMENT,
    enabled BOOLEAN,
    username VARCHAR(12) ,
    password VARCHAR(255),
    CONSTRAINT pk_id_users PRIMARY KEY (id),
    CONSTRAINT Uq_user UNIQUE (username)
);

CREATE TABLE roles (
    id INT AUTO_INCREMENT,
    name VARCHAR(255),
    CONSTRAINT pk_id_roles PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    user_id INT,
    role_id INT,
    CONSTRAINT users_roles_ids UNIQUE (role_id, user_id),
    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Surveys

CREATE TABLE surveys (
    id INT AUTO_INCREMENT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    description VARCHAR(255),
    name VARCHAR(255),

    CONSTRAINT pk_id_surveys PRIMARY KEY (id)
);

CREATE TABLE chapter (
    id INT AUTO_INCREMENT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    chapter_number VARCHAR(50),
    chapter_title VARCHAR(50),

    survey_id INT,

    CONSTRAINT pk_id_chapter PRIMARY KEY (id),
    CONSTRAINT fk_chapter_surveys FOREIGN KEY (survey_id) REFERENCES surveys (id)
);

CREATE TABLE questions (
    id INT AUTO_INCREMENT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    question_number VARCHAR(10),
    response_type VARCHAR(20),
    comment_question TEXT,
    question_text TEXT,

    chapter_id INT,

    CONSTRAINT pk_id_question PRIMARY KEY (id),
    CONSTRAINT fk_questions_chapter FOREIGN KEY (chapter_id) REFERENCES chapter (id)
);

CREATE TABLE categories_catalog (
    id INT AUTO_INCREMENT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    name VARCHAR(255),

    CONSTRAINT pk_id_categories_catalog PRIMARY KEY (id)
);

CREATE TABLE response_options (
    id INT AUTO_INCREMENT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    option_value INT,
    typecomponenthtml VARCHAR(30),
    comment_response TEXT,
    option_text TEXT,

    categorycatalog_id INT,
    question_id INT,

    parentresponse_id INT,  

    CONSTRAINT pk_id_response_options PRIMARY KEY (id),
    CONSTRAINT fk_res_opts_cats_catg FOREIGN KEY (categorycatalog_id) REFERENCES categories_catalog (id),
    CONSTRAINT fk_res_opts_questions FOREIGN KEY (question_id) REFERENCES questions (id),
    CONSTRAINT fk_res_opts_res_opts FOREIGN KEY (parentresponse_id) REFERENCES response_options (id)
);

CREATE TABLE subresponse_options (
    id INT AUTO_INCREMENT,
    subresponse_number INT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    component_html VARCHAR(255),
    subresponse_text VARCHAR(255),

    responseoptions_id INT,

    CONSTRAINT pk_id_subresponse_options PRIMARY KEY (id),
    CONSTRAINT fk_sub_res_opts FOREIGN KEY (responseoptions_id) REFERENCES response_options (id)
);

CREATE TABLE response_question (
    id INT AUTO_INCREMENT,
    response_id INT,
    subresponse_id INT,
    responsetext VARCHAR(80),

    CONSTRAINT pk_id_response_question PRIMARY KEY (id),
    CONSTRAINT fk_res_quest_res_opts FOREIGN KEY (response_id) REFERENCES response_options (id),
    CONSTRAINT fk_res_quest_sub_res_opts FOREIGN KEY (subresponse_id) REFERENCES subresponse_options (id)
);