<?php

	try {
		$pdo = new PDO("mysql:host=192.168.25.23;dbname=financa", "root", "123456");
		echo "conectado";
	} catch (PDOException $e) {
		echo $e->getMessage();
	}

?>