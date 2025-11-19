# Test Cases: QA Railway Website

| TC ID | TC Description | Steps | Expected Behavior |
| :--- | :--- | :--- | :--- |
| **TC01** | User can log into Railway with valid username and password | 1. Navigate to QA Railway Website<br>2. Click on "Login" tab<br>3. Enter valid Email and Password<br>4. Click on "Login" button | User is logged into Railway. Welcome user message is displayed. |
| **TC02** | User can't login with blank "Username" textbox | 1. Navigate to QA Railway Website<br>2. Click on "Login" tab<br>3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox<br>4. Click on "Login" button | User can't login and message "There was a problem with your login and/or errors exist in your form." appears. |
| **TC03** | User cannot log into Railway with invalid password | 1. Navigate to QA Railway Website<br>2. Click on "Login" tab<br>3. Enter valid Email and invalid Password<br>4. Click on "Login" button | Error message "There was a problem with your login and/or errors exist in your form." is displayed |
| **TC04** | Login page displays when un-logged User clicks on "Book ticket" tab | 1. Navigate to QA Railway Website<br>2. Click on "Book ticket" tab | Login page displays instead of Book ticket page |
| **TC05** | System shows message when user enters wrong password several times | 1. Navigate to QA Railway Website<br>2. Click on "Login" tab<br>3. Enter valid information into "Username" textbox except "Password" textbox.<br>4. Click on "Login" button<br>5. Repeat step 3 three more times. | User can't login and message "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes." appears. |
| **TC06** | Additional pages display once user logged in | 1. Navigate to QA Railway Website<br>2. Click on "Login" tab<br>3. Login with valid account | "My ticket", "Change password" and "Logout" tabs are displayed.<br><br>Click "My ticket" tab, user will be directed to My ticket page<br><br>Click "Change password" tab, user will be directed to Change password page |
| **TC07** | User can create new account | 1. Navigate to QA Railway Website<br>2. Click on "Register" tab<br>3. Enter valid information into all fields<br>4. Click on "Register" button | New account is created and message "Thank you for registering your account" appears. |
| **TC08** | User can't login with an account hasn't been activated | **Pre-condition:** Create a new account but do not activate it<br>1. Navigate to QA Railway Website<br>2. Click on "Login" tab<br>3. Enter username and password of account hasn't been activated.<br>4. Click on "Login" button | User can't login and message "Invalid username or password. Please try again." appears. |
| **TC09** | User can change password | **Pre-condition:** Create and activate a new account<br>1. Navigate to QA Railway Website<br>2. Login with valid account<br>3. Click on "Change Password" tab<br>4. Enter valid value into all fields.<br>5. Click on "Change Password" button | Message "Your password has been updated" appears. |
| **TC10** | User can't create account with "Confirm password" is not the same with "Password" | 1. Navigate to QA Railway Website<br>2. Click on "Register" tab<br>3. Enter valid information into all fields except "Confirm password" is not the same with "Password"<br>4. Click on "Register" button | Message "There're errors in the form. Please correct the errors and try again." appears. |
| **TC11** | User can't create account while password and PID fields are empty | 1. Navigate to QA Railway Website<br>2. Click on "Register" tab<br>3. Enter valid email address and leave other fields empty<br>4. Click on "Register" button | Message "There're errors in the form. Please correct the errors and try again." appears above the form.<br><br>Next to password fields, error message "Invalid password length" displays.<br><br>Next to PID field, error message "Invalid ID length" displays |
| **TC12** | Errors display when password reset token is blank | **Pre-condition:** Create and activate a new account<br>1. Navigate to QA Railway Login page<br>2. Click on "Forgot Password page" link<br>3. Enter the email address of the created account in Pre-condition<br>4. Click on "Send Instructions" button<br>5. Open mail box and click on reset password link<br>6. Enter new passwords and remove the Password Reset Token<br>7. Click "Reset Password" button | "Password Change Form" page displays.<br><br>Error message "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one." displays above the form.<br><br>Error message "The password reset token is invalid." displays next to the "Password Reset Token" field. |
| **TC13** | Errors display if password and confirm password don't match when resetting password | **Pre-condition:** Create and activate a new account<br>1. Navigate to QA Railway Login page<br>2. Click on "Forgot Password page" link<br>3. Enter the email address of the created account in Pre-condition<br>4. Click on "Send Instructions" button<br>5. Open mail box and click on reset password link<br>6. Enter different values for password fields.<br>7. Click "Reset Password" button | "Password Change Form" page displays<br><br>Error message "Could not reset password. Please correct the errors and try again." displays above the form.<br><br>Error message "The password confirmation did not match the new password." displays next to the confirm password field. |
| **TC14** | User can book 1 ticket at a time | **Pre-condition:** Create and activate a new account<br>1. Navigate to QA Railway Website<br>2. Login with a valid account<br>3. Click on "Book ticket" tab<br>4. Select a "Depart date" from the list<br>5. Select "Sài Gòn" for "Depart from" and "Nha Trang" for "Arrive at"<br>6. Select "Soft bed with air conditioner" for "Seat type"<br>7. Select "1" for "Ticket amount"<br>8. Click on "Book ticket" button | Message "Ticket booked successfully!" displays. Ticket information display correctly (Depart Date, Depart Station, Arrive Station, Seat Type, Amount) |
| **TC15** | User can open "Book ticket" page by clicking on "Book ticket" link in "Train timetable" page | **Pre-condition:** Create and activate a new account<br>1. Navigate to QA Railway Website<br>2. Login with a valid account<br>3. Click on "Timetable" tab<br>4. Click on "Book ticket" link of the route from "Huế" to "Sài Gòn" | "Book ticket" page is loaded with correct "Depart from" and "Arrive at" values. |
| **TC16** | User can cancel a ticket | **Pre-condition:** Create and activate a new account<br>1. Navigate to QA Railway Website<br>2. Login with a valid account<br>3. Book a ticket<br>4. Click on "My ticket" tab<br>5. Click on "Cancel" button of ticket which user want to cancel.<br>6. Click on "OK" button on Confirmation message "Are you sure?" | The canceled ticket is disappeared. |

## How to Setup and Run Test Cases

This guide provides instructions on how to set up the project and execute the automated test cases. These instructions apply across all test case branches.

### Prerequisites

Before you begin, ensure you have the following software installed:

*   **Java Development Kit (JDK) 8 or higher:** [Download from Oracle](https://www.oracle.com/java/technologies/javase-downloads.html)
*   **Apache Maven:** [Download and install from Apache Maven](https://maven.apache.org/download.cgi)
*   **Git:** [Download from Git-SCM](https://git-scm.com/downloads)
*   **Google Chrome Browser:** Ensure you have a recent version installed.
*   **ChromeDriver:** Download the ChromeDriver executable that matches your Chrome browser version. Place it in a directory that is included in your system's PATH environment variable, or specify its path in `config.properties`. [Download from ChromeDriver Downloads](https://chromedriver.chromium.org/downloads)

### 1. Clone the Repository

Open your terminal or command prompt and clone the repository:

```bash
git clone https://github.com/Andlpde150032/Andlpde150032-selenium1-railways-AnDuong.git
cd Andlpde150032-selenium1-railways-AnDuong
```

### 2. Switch to a Specific Test Case Branch (Optional)

Each test case (TC) is implemented in its own dedicated branch. To work on a specific TC, switch to its branch:

```bash
git checkout TC01-Login
# Or for other TCs:
git checkout TC02-Login-Blank-Username
git checkout TC03-Login-Invalid-Password
git checkout TC04-Login-BookTicket-Redirect
git checkout TC05-Login-Multiple-Wrong-Password
git checkout TC06-Login-Additional-Pages
git checkout TC07-Register-New-Account
git checkout TC08-Login-Unactivated-Account
git checkout TC09-Change-Password
git checkout TC10-Register-Mismatched-Passwords
git checkout TC11-Register-Empty-Password-PID
git checkout TC14-Book-Ticket
git checkout TC15-Timetable-Book-Ticket-Link
git checkout TC16-Cancel-Ticket
```

To return to the main development branch:

```bash
git checkout master
```

### 3. Configuration

The project uses `config.properties` and `test-data.json` for configuration and test data.

*   **`src/test/resources/config.properties`**:
    *   `base.url`: The base URL of the QA Railway Website.
    *   `driver.path`: (Optional) Path to your ChromeDriver executable if not in system PATH.
    *   `implicit.wait`: Implicit wait time in seconds for WebDriver.

    Example `config.properties`:
    ```properties
    base.url=http://railwayb2.somee.com/
    driver.path=
    implicit.wait=10
    ```

*   **`src/test/resources/test-data.json`**:
    This file contains test data for various scenarios, such as login credentials, registration details, etc. Update these values as needed for your testing environment.

    Example `test-data.json` snippet:
    ```json
    {
      "login": {
        "email": "your_valid_email@example.com",
        "password": "your_valid_password"
      },
      "unactivated_login": {
        "email": "unactivated_email@example.com",
        "password": "unactivated_password"
      },
      "register": {
        "email": "new_user_email@example.com",
        "password": "new_user_password",
        "pid": "123456"
      },
      "register_empty_fields": {
        "email": "empty_fields_email@example.com"
      },
      "new_password": {
        "password": "new_secure_password"
      }
    }
    ```

    **Note for TC08 (Unactivated Account):** The automated test for TC08 requires an account that has been created but not activated. This pre-condition is typically set up manually or through an existing test account. The test will proceed assuming such credentials are provided in `test-data.json` under `unactivated_login`.

### 4. ChromeDriver Setup

Ensure ChromeDriver is correctly configured:

1.  **Download:** Get the ChromeDriver version matching your Google Chrome browser from [ChromeDriver Downloads](https://chromedriver.chromium.org/downloads).
2.  **Placement:**
    *   **Recommended:** Place the `chromedriver.exe` (or `chromedriver` on Linux/macOS) in a directory that is part of your system's PATH environment variable.
    *   **Alternative:** Place `chromedriver.exe` in the root of your project directory or specify its absolute path in the `driver.path` property in `src/test/resources/config.properties`.

### 5. Running Test Cases

You can run test cases using Apache Maven.

*   **Run all tests in the project:**

    ```bash
    mvn clean test
    ```

*   **Run a specific test class:**

    You can specify a single test class to run using the `-Dtest` parameter. For example, to run `LoginPageTest_TC01`:

    ```bash
    mvn clean test -Dtest=LoginPageTest_TC01
    ```

    To run a test from a specific package, include the full package name:

    ```bash
    mvn clean test -Dtest=demo.auth.LoginPageTest_TC01
    ```

    Apply this pattern for any specific test class you wish to execute (e.g., `demo.dashboard.BookTicketTest_TC14`).

*   **Run tests from a specific package:**

    You can run all tests within a package using wildcards:

    ```bash
    mvn clean test -Dtest=demo.auth.*
    ```

This guide should help you get started with running the automated test cases for the QA Railway Website.
