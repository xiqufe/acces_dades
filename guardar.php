<?php
if(isset($_POST["varBusqueda"])){
$varBusqueda = $_POST["varBusqueda"];
$varNombreOficial = $_POST["varNombreOficial"];
$varNombreComun = $_POST["varNombreComun"];
$varDominio = $_POST["varDominio"];
$varRegion = $_POST["varRegion"];
$varMoneda = $_POST["varMoneda"];
$varBandera = $_POST["varBandera"];
$servidor = "localhost";
$usuario = "root";
$password = "";
$dbname = "paises";
$conexion = mysqli_connect($servidor, $usuario, $password, $dbname);
if (!$conexion) {
echo "Error en la conexion a MySQL: ".mysqli_connect_error();
exit();
}
$sql = "INSERT INTO busquedas (busqueda,nombre_oficial,nombre_comun,dominio,region,moneda,bandera) VALUES ('".$varBusqueda."','".$varNombreOficial."','".$varNombreComun."','".$varDominio."','".$varRegion."','".$varMoneda."','".$varBandera."')";
if (mysqli_query($conexion, $sql)) {
echo "Registro insertado correctamente.";
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
}
}
?>
