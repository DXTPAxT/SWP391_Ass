<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List,models.Shipping" %>
<%
    List<Shipping> shippingList = (List<Shipping>) request.getAttribute("shippingList");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>My Shipping Orders</title>
        <style>
            body {
                font-family: 'Segoe UI', Arial, sans-serif;
                background: #f4f7fa;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 950px;
                margin: 40px auto;
                background: #fff;
                padding: 36px 40px 32px 40px;
                border-radius: 14px;
                box-shadow: 0 8px 32px rgba(60,60,100,0.13);
            }
            h2 {
                text-align: center;
                color: #2d3a4b;
                margin-bottom: 30px;
                font-size: 2.1rem;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 18px;
                background: #f8fafc;
                border-radius: 8px;
                overflow: hidden;
            }
            th, td {
                padding: 14px 10px;
                border-bottom: 1px solid #e0e0e0;
                text-align: center;
                font-size: 15px;
            }
            th {
                background: linear-gradient(90deg, #1976d2 60%, #42a5f5 100%);
                color: #fff;
                font-size: 16px;
                letter-spacing: 0.5px;
            }
            tr:last-child td {
                border-bottom: none;
            }
            tr:hover {
                background: #e3f0fc;
                transition: background 0.2s;
            }
            .status {
                font-weight: bold;
                color: #1976d2;
            }
            .empty {
                text-align: center;
                color: #888;
                font-size: 17px;
                padding: 40px 0;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>My Shipping Orders</h2>
            <table>
                <tr>
                    <th>Shipping ID</th>
                    <th>Order ID</th>
                    <th>Shipper ID</th>
                    <th>Status</th>
                    <th>Feedback</th>
                </tr>
                <%
                    if (shippingList == null || shippingList.isEmpty()) {
                %>
                <tr>
                    <td colspan="5" class="empty">You have no shipping orders yet.</td>
                </tr>
                <%
                    } else {
                        for (Shipping ship : shippingList) {
                %>
                <tr>
                    <td><%= ship.getShippingID() %></td>
                    <td><%= ship.getOrderID() %></td>
                    <td><%= ship.getShipperID() %></td>
                    <td class="status"><%= ship.getShippingStatus() %></td>
                    <td><%= ship.getFeedback() %></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
    </body>
</html>