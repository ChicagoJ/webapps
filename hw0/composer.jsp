<html>
  <head>
    <title>Composer Information</title>

    <link rel="stylesheet" type="text/css" href="stylesheet.css">
  </head>
  <body>

    <table>
      <tr>
        <th colspan="2">Composer Information</th>
      </tr>
      <tr>
        <td>Name: </td>
        <td>${requestScope.composer.productName}</td>
      </tr>
      <tr>
        <td>Category: </td>
        <td>${requestScope.composer.productClass}</td>
      </tr>
      <tr>
        <td>ID: </td>
        <td>${requestScope.composer.id}</td>
      </tr>
      <tr>
        <td>Price: </td>
        <td>${requestScope.composer.productPrice}</td>
      </tr>      
    </table>
    <p>Go to <a href="itemlist.jsp?productClass=${requestScope.composer.productClass}" class="link">itemlist</a>.</p>
    <p>Go back to <a href="index.jsp" class="link">application home</a>.</p>
  </body>
</html>

