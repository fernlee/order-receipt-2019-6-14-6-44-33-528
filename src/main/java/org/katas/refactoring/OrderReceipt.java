package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        printHeaders(output);

        output.append(o.getCustomerName());
        output.append(o.getCustomerAddress());

        double totSalesTax = 0d;
        double totalPrice = 0d;
        for (LineItem lineItem : o.getLineItems()) {
            printLineItem(output, lineItem);

            final double totalItemPrice = lineItem.totalItemPrice();
            totSalesTax = getTotSalesTax(totSalesTax, totalItemPrice);

            totalPrice += totalItemPrice;
        }

        totalPrice += totSalesTax;
        output.append("Sales Tax").append('\t').append(totSalesTax);

        output.append("Total Amount").append('\t').append(totalPrice);
        return output.toString();
    }

    private double getTotSalesTax(double totSalesTx, double totalAmount) {
        double salesTax = totalAmount * .10;
        totSalesTx += salesTax;
        return totSalesTx;
    }

    private void printLineItem(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalItemPrice());
        output.append('\n');
    }

    private void printHeaders(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }
}