
# Step 1: Electronics + 18% GST
def calculateElectronicsGST(a, b):
    amount = a + b
    return amount + (amount * 18 / 100)


# Step 2: Fashion + 13% GST
def calculateFashionGST(c, d):
    amount = c + d
    return amount + (amount * 13 / 100)


# Step 3: Food + 7% GST
def calculateFoodGST(e):
    return e + (e * 7 / 100)


# Step 4: Total amount after GST
def totalBill(a, b, c, d, e):
    electronics = calculateElectronicsGST(a, b)
    fashion = calculateFashionGST(c, d)
    food = calculateFoodGST(e)

    return electronics + fashion + food


# Calculate the 20% discount
def calculateDiscount(total):
    return total * 20 / 100


# Step 5: Final payable amount
def calculateDiscountedBill(a, b, c, d, e):
    total = totalBill(a, b, c, d, e)
    discount = calculateDiscount(total)

    return total - discount


# Sample values supplied directly as arguments
a, b, c, d, e = 100, 100, 100, 100, 100

electronics = calculateElectronicsGST(a, b)
fashion = calculateFashionGST(c, d)
food = calculateFoodGST(e)

total = totalBill(a, b, c, d, e)
discount = calculateDiscount(total)
payable = calculateDiscountedBill(a, b, c, d, e)

print(f"Electronics after GST: Rs. {electronics:.2f}")
print(f"Fashion after GST: Rs. {fashion:.2f}")
print(f"Food after GST: Rs. {food:.2f}")
print(f"Total after GST: Rs. {total:.2f}")
print(f"Discount: Rs. {discount:.2f}")
print(f"Final payable amount: Rs. {payable:.2f}")
