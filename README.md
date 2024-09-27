# Patient Management Application

## Overview

This Java-based application allows healthcare professionals to manage patient data efficiently. It provides a graphical user interface (GUI) for adding new patients and viewing patient's medical records through searching based on their personal information (first name, last name, date of birth). The application focuses on user-friendly input of patient details, such as height, weight, allergies, and medical conditions.

## Features

- **User Authentication**: A simple login form that requires a username and password to access patient data.
  
- **Add Patient**: After logging in, users can input new patient data such as:
  - First and last name
  - Date of birth
  - Weight and height
  - Allergies (comma-separated)
  - Medical conditions (comma-separated)
  
- **Patient Lookup**: Users can search for an existing patient using their first name, last name, and date of birth. The search retrieves and displays all of the patient's details.

- **Data Submission**: Inputted patient data is submitted and stored in a Postgres database for future retrieval.

- **User Logout**: The application allows users to log out, returning them to the login screen.

## Application Structure

The application consists of the following components:

### `Application.java`
This file serves as the main entry point of the application. It handles the initial setup and launching of the GUI interface.

### `GuiController.java`
This file contains the logic for managing the graphical interface and handling events such as form submissions, searches, and logins.

### `Launcher.java`
This file facilitates the launching of the GUI and controls the execution flow of the application.

## How to Use

### 1. Launching the Application

- A pre-built `.jar` file is included in this repository, making it easy to launch the application without the need for compiling the source code or using the command line.
  
  **To launch the application**:
  - Double-click the `.jar` file (make sure you have Java installed).
  
  **Or from the command line** (if preferred):
  ```bash
  java -jar PatientManagementApp.jar

