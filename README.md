# qa-challenge

# Explanation for second task (Automation Testing)

## Sauce Demo Automation Test (TestNG + Selenium + Java)

This test automates the full login → checkout → logout flow on the [saucedemo.com](https://saucedemo.com) website.

### What It Covers:
1. Login using:
   - **Username**: `standard_user`
   - **Password**: `secret_sauce`
2. Add a product to cart
3. Verify the product in the cart
4. Proceed to checkout and fill in the required details
5. Verify product appears during checkout
6. Finish checkout and confirm successful order
7. Logout from the application

### Tech Stack:
- Java
- Selenium WebDriver
- TestNG

### Notes:
- The script uses basic locators (ID, CSS selectors, and class names).
- Assertions are placed after each major action to ensure flow correctness.
- The test is prioritized using TestNG `priority` attributes.
