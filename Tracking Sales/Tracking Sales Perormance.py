#names:
#Huthaifa Rajha


def read_stores():
    try:
        #opening the file
        file = open("stores.txt", "r")
        #creating a dictionary to store the data
        stores_data = {}
        file.readline()
        #reading the file
        for line in file:
            line = line.strip().split(",")
            store_id = int(line[0])
            store_name = line[1]
            store_location = line[2]
            stores_data[store_id] = (store_name, store_location)
        file.close()
        return stores_data
    except IOError as i:
        print("Invalid File",i)

def read_sales():
    try:
        #opening the file
        file = open("sales_data.txt", "r")
        #creating a dictionary to store the data
        sales_data = {}
        file.readline()
        lines = file.readlines()
        #reading the file
        for id in range(1, 11):
            #creating a list to store the data
            data = []
            for line in lines:
                line = line.strip().split(",")
                sales_id = int(line[0])
                sales_costs = int(line[3])
                if id == sales_id:
                    data.append(sales_costs)
            sales_data[id] = tuple(data)
        file.close()
        return sales_data
    except IOError as i:
        print("Invalid File",i)
def average_annual_sales(sales_data, stores_data):
    try:
        import math
        #showing the header
        print(f"{'ID':9}{'Name':19}Average Annual Sales")
        #calculating the average annual sales
        for store_id, sales in sales_data.items():
            #sum of the data
            total_sales = sum(sales)
            # the period given (2023-2018) = 6
            years = 6
            # displaying store names
            store_name = stores_data[store_id][0]
            avg_sale = total_sales / years
            avg_sale=math.ceil(avg_sale)
            #showing the output
            print(f"{store_id}\t{store_name:20}{avg_sale}")
    except Exception as e:
        print("An error occurred:", e)

def store_profit_year(store_id, year):
    try:
        year_sales = 0
        year_cost = 0

        # Read from sales_data.txt
        sale_file = open('sales_data.txt', 'r')
        # Loop through the file and add the sales of the store in the given year
        for line in sale_file:
            # Split the line into a list of data
            sale_data = line.strip().split(',')
            # Check if the store id and year match
            if sale_data[0] == str(store_id) and int(sale_data[1]) == year:
                # Add the sales to the total sales
                year_sales += int(sale_data[3])
        sale_file.close()

        # Read from costs.txt
        cost_file = open('costs.txt', 'r')
        for line in cost_file:
            cost_data = line.strip().split(':')
            if cost_data[0] == str(store_id) and int(cost_data[1]) == year:
                year_cost = int(cost_data[2])
        cost_file.close()

        # Calculate profit and write to store_profit.txt
        profit = year_sales - year_cost
        # If the sales are less than the cost, the profit is 0
        if year_sales < year_cost:
            profit = 0
        store_profit = open('store_profit.txt', 'a')
        store_profit.write(f"{store_id},{year},{profit}\n")
        store_profit.close()
        
        # Print the results
        print(f'Total Sales of store {store_id} in year {year} is {year_sales}')
        print(f'Cost of store {store_id} in year {year} is {year_cost}')
        print(f'The profit of the store with id {store_id} in year {year} is {profit}')
        print('The data added to the file titled: store_profit.txt')

    except IOError as f:
        print("File not found", f)
    except Exception as e:
        print("An error occurred:", e)

def top_three_stores(sales_data, stores_data):
    top_three = []

    # Finding the top three stores
    for i in range(3):
        max_sales = -1
        max_store_id = ''

        # Find the store with maximum sales
        for store_id, sales in sales_data.items():
            total_sales = sum(sales)
            # Calculate total sales for the store
            found = False
            # Check if the store is already in the top three
            for item in top_three:
                if item[0] == store_id:
                    found = True
                    break
            # If the store is not in the top three, check if it is the new top store
            if not found and total_sales > max_sales:
                max_sales = total_sales
                max_store_id = store_id
        # Add the store to the top three
        top_three.append((max_store_id, max_sales))

    # Printing top three stores
    print("The top 3 stores based on total sales are:")
    print(f"{'ID':9}Name\t\t\tTotal Sale")
    # Print the store name and total sales
    for store_id, total_sales in top_three:
        store_name = stores_data[store_id][0]
        print(f"{store_id}\t{store_name:16}\t{total_sales}")

def rate_stores(sales_data, stores_data):
    print(" Retail Stores Rating:")
    for store_id, sales in sales_data.items():
        # Calculate average monthly sales
        avg_monthly_sales = sum(sales) // len(sales)
        rating = ""
        # Determine the rating based on the average monthly sales
        if avg_monthly_sales > 95000:
            rating = "*****"
        elif 85000 <= avg_monthly_sales <= 95000:
            rating = "****"
        elif 75000 <= avg_monthly_sales < 85000:
            rating = "***"
        elif 50000 <= avg_monthly_sales < 75000:
            rating = "**"
        else:
            rating = "*"
        # Print the store name and rating
        name = stores_data[store_id][0]
        print(f"{name:22}{rating}")

def main():
    stores_data = read_stores()
    sales_data = read_sales()
    # Print welcome message
    print("Welcome to tracking sales performance of retail stores program!")
    print("---------------------------------------------------------------")
    # Print menu
    while True:
        print("Please select one of the following:")
        print("1. Average Annual Sales")
        print("2. Profit of a store in a year")
        print("3. Top three performing stores")
        print("4. Rate the stores average sales")
        print("5. Exit")
        
        try:
            # Get user choice
            choice = int(input("Enter your choice : "))
            if choice == 1:
                average_annual_sales(sales_data, stores_data)
            elif choice == 2:
                # Get store ID and year from user
                store_id = int(input("Enter store ID: "))
                # Validate store ID
                while store_id < 1 or store_id > 10:
                    print("Invalid store ID. Please enter a number between 1 and 10.")
                    store_id = int(input("Enter store ID: "))
                year = int(input("Enter year: "))
                # Validate year
                while year < 2018 or year > 2023:
                    print("Invalid year. Please enter a year between 2018 and 2023.")
                    year = int(input("Enter year: "))
                store_profit_year(store_id, year)
            elif choice == 3:
                top_three_stores(sales_data, stores_data)
            elif choice == 4:
                rate_stores(sales_data, stores_data)
                # Get store ID and year from user
            elif choice == 5:
                print('Exiting the program')
                break
            else:
                # Invalid choice
                print("Invalid choice please enter a number between 1 and 5")
        except ValueError as vrr:
            print("Invalid choice.",vrr)

main()