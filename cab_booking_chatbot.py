# Kiran Academy - Task 02: Cab Booking Chatbot
# Student Name: Tejas Jagdale

import random

# Predefined cab rates (rupees per kilometre)
cab_rates = {
    1: ("Auto", 12),
    2: ("Sedan", 18),
    3: ("SUV", 25)
}

# Predefined routes: source, destination, distance in kilometres
routes = {
    1: ("Pune Station", "Shivajinagar", 5),
    2: ("Swargate", "Kothrud", 8),
    3: ("Hinjawadi", "Wakad", 10)
}

while True:
    print("\n========== CAB BOOKING CHATBOT ==========")
    print("1. Book Ride")
    print("2. Exit")

    try:
        main_choice = int(input("Enter your choice: "))
    except ValueError:
        print("Invalid input. Please enter 1 or 2.")
        continue

    if main_choice == 1:
        print("\nChoose your cab type:")
        print("1. Auto  - Rs. 12/km")
        print("2. Sedan - Rs. 18/km")
        print("3. SUV   - Rs. 25/km")

        try:
            cab_choice = int(input("Enter cab choice: "))
        except ValueError:
            print("Invalid input. Please enter a number.")
            continue

        if cab_choice not in cab_rates:
            print("Invalid cab choice. Returning to the main menu.")
            continue

        cab_name, rate = cab_rates[cab_choice]

        print("\nChoose your route:")
        for route_no, (source, destination, distance) in routes.items():
            print(f"{route_no}. {source} to {destination} ({distance} km)")

        try:
            route_choice = int(input("Enter route number: "))
        except ValueError:
            print("Invalid input. Please enter a route number.")
            continue

        if route_choice not in routes:
            print("Invalid route choice. Returning to the main menu.")
            continue

        source, destination, distance = routes[route_choice]
        fare = rate * distance

        print("\n------------- RIDE DETAILS -------------")
        print(f"Cab type       : {cab_name}")
        print(f"Source         : {source}")
        print(f"Destination    : {destination}")
        print(f"Distance       : {distance} km")
        print(f"Rate per km    : Rs. {rate}")
        print(f"Total fare     : Rs. {fare}")
        print("----------------------------------------")

        print("\n1. Confirm & Share OTP")
        print("2. Cancel Ride")

        try:
            confirm_choice = int(input("Enter your choice: "))
        except ValueError:
            print("Invalid input. Booking cancelled; returning to the main menu.")
            continue

        if confirm_choice == 2:
            print("Ride cancelled. No OTP has been shared.")
            continue
        elif confirm_choice == 1:
            otp = random.randint(1000, 9999)
            print(f"Your ride OTP is: {otp}")
            print("Booking confirmed successfully!")
            print("IMPORTANT: Once the OTP is shared, the ride cannot be cancelled.")
            print("Please provide the OTP to the driver when the ride begins.")
        else:
            print("Invalid confirmation choice. No OTP has been shared.")
            print("Returning to the main menu.")
            continue

    elif main_choice == 2:
        print("Thank you for using the Cab Booking Chatbot. Goodbye!")
        break

    else:
        print("Invalid choice. Please select 1 or 2.")
