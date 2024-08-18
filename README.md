Software Requirements Specification (SRS) for Rent-A-Car App
1. Introduction
•	The Rent-A-Car App project aims to develop a mobile application and backend system for users to rent vehicles, manage reservations, and facilitate the car rental process.
2. Project Overview
•	The Rent-A-Car App project is sponsored by BEUto provide a platform for individuals to rent vehicles from a car rental service with ease and convenience.
3. Scope
•	The Rent-A-Car App will include the following key features:
o	User registration and authentication
o	Vehicle selection and booking
o	Reservation management
o	Pick-up and return scheduling
o	Secure payment processing
o	User profile management
o	Integration with car rental fleet management systems
•	Constraints and assumptions:
o	The app will support Android platforms.
o	Vehicle selection will include various types and models.
o	Integration with car rental fleet management systems may require custom integration work.
o	Data security and compliance with data privacy regulations are mandatory.
4. Functional Requirements
4.1 Mobile App
•	User Registration:
o	Users can create accounts with personal information.
o	Users can log in and reset their passwords.
•	Vehicle Selection and Booking:
o	Users can search for available vehicles, view details, and book the desired vehicle.
o	Users can specify rental dates, pick-up locations, and return locations.
•	Reservation Management:
o	Users can view and manage their reservations, including modifying or canceling bookings.
•	Pick-up and Return Scheduling:
o	Users can schedule pick-up and return times for rented vehicles.
•	User Profile Management:
o	Users can update their profile information.
•	Integration with Car Rental Fleet Management Systems:
o	Enable integration with car rental fleet management systems for vehicle availability and inventory management.
4.2 Backend
•	Database:
o	Use a database to store user profiles, vehicle data, reservations, and payment information.
•	API Endpoints:
o	Define RESTful API endpoints for user registration, vehicle availability, reservation management, and user profiles.
•	Reservation and Payment Processing:
o	Implement mechanisms for processing reservations and payments.
•	Security:
o	Implement measures for data protection, encryption, and access control.
•	Integration with Car Rental Fleet Management Systems:
o	Enable integration with car rental fleet management systems for vehicle availability and management.
5. Non-Functional Requirements
•	Performance:
o	Response time: Within 2 seconds for most user interactions.
o	Availability: 99.9% uptime.
•	Usability:
o	Intuitive user interface and a user-friendly experience.
•	Reliability:
o	Implement failover and backup systems for data redundancy.
•	Compatibility:
o	Support Android devices.
•	Data Backup and Recovery:
o	Regular data backups and a disaster recovery plan.
•	Legal and Compliance:
o	Ensure compliance with data privacy regulations and industry standards.
•	Documentation:
o	Provide user manuals and API documentation.
6. System Architecture
•	The system will use a multi-tier architecture, consisting of a mobile app interface, a RESTful API, and a database hosted on Local Computer.
7. Test Requirements
•	Testing will include unit testing, integration testing, and user acceptance testing. Test cases will be documented and executed.
8. Timeline and Milestones
•	The project timeline includes development, testing, and deployment phases with specific milestones.
![image](https://github.com/BakuEngineeringUniversity/prj-rent-a-car-app-2023/assets/44253460/46b32ce7-5ef2-4531-b5f6-cee7c7da3e18)
