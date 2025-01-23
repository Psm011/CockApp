<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment</title>
    <%@ include file="component/common_css_js.jsp"%>
    <%@ include file="component/navbar.jsp"%>
    <%@ include file="component/common_models.jsp"%>
</head>
<body>
    <div class="container px-5 mt-5">
        <div class="col-md-6 mx-auto">
            <div class="card">
                <div class="card-header blue text-white text-center">
                    <h3>Complete Your Payment</h3>
                </div>
                <div class="card-body">
                    <p>Please complete your payment to finalize the order.</p>
                    <form action="CompletePaymentServlet" method="post">
                        <div class="form-group">
                            <label for="paymentMethod">Select Payment Method:</label>
                            <select class="form-control" id="paymentMethod" name="paymentMethod" required>
                                <option value="">--Select Payment Method--</option>
                                <option value="creditCard">Credit Card</option>
                                <option value="bharatPay">Bharat Pay</option>
                                <option value="paypal">PayPal</option>
                                <option value="netBanking">Net Banking</option>
                            </select>
                        </div>
                        <!-- Payment details sections -->
                        <div id="creditCardDetails" style="display: none;">
                            <div class="form-group">
                                <label for="cardNumber">Card Number:</label>
                                <input type="text" class="form-control" id="cardNumber" name="cardNumber" required>
                            </div>
                            <div class="form-group">
                                <label for="cardName">Name on Card:</label>
                                <input type="text" class="form-control" id="cardName" name="cardName" required>
                            </div>
                            <div class="form-group">
                                <label for="expiryDate">Expiry Date:</label>
                                <input type="text" class="form-control" id="expiryDate" name="expiryDate" placeholder="MM/YY" required>
                            </div>
                            <div class="form-group">
                                <label for="cvv">CVV:</label>
                                <input type="text" class="form-control" id="cvv" name="cvv" required>
                            </div>
                        </div>
                        <!-- More payment options... -->
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Complete Payment</button>
                            <button type="button" class="btn btn-outline-secondary" onclick="window.location.href='index.jsp'">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.getElementById('paymentMethod').addEventListener('change', function() {
            var creditCardDetails = document.getElementById('creditCardDetails');
            creditCardDetails.style.display = (this.value === 'creditCard') ? 'block' : 'none';
            // Handle other payment methods similarly...
        });

        // Debugging submission
        document.querySelector('form').addEventListener('submit', function(e) {
            console.log('Form submitted');
        });
    </script>
</body>
</html>
