<!DOCTYPE html>
<html>
<head>
		<link type="text/css" rel="stylesheet" href="stylesheet.css"/>
		<title></title>
	</head>
<body>

<h2>Welcome to CS242!</h2>

<?php
	include 'print.php';
	$parser = new Printer();
	$parser->get_data();
	$parser->print_data();
?>


</body>
</html>