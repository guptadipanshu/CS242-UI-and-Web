<?php
//intialize the database
$mysqli= mysqli_connect('engr-cpanel-mysql.engr.illinois.edu','dgupta13_test','asd123','dgupta13_MYTEST');
$name=test_input($_POST["name"]); //remove all extra tokens
$comment=test_input($_POST["comment"]); //remove extra tokens
$comment=filter_comment($comment,$mysqli); //replace words from database
$child=$_POST["revision"];
$reply=$_POST["reply"];

$res=mysqli_query($mysqli,"SELECT * FROM TEST WHERE CHILD=$child ORDER BY UID DESC LIMIT 1");
//get the maximum uid 
while($row = mysqli_fetch_array($res))
{
     $uid=$row['UID'];
}
$uid++;
//to avoid sql injection use sprintf
$query = sprintf("INSERT INTO `dgupta13_MYTEST`.`TEST` (`UID`, `NAME`, `COMMENT`, `CHILD`,`REPLY`) VALUES ('$uid','%s' ,'%s' , '$child','$reply')",
                  $mysqli->real_escape_string($name),
                  $mysqli->real_escape_string($comment));
//run the query
mysqli_query($mysqli,$query);

header("Location: index.php");
/*Function that avoids form invalidation
*@param data-text entered by user
*return data after parse 
*/
function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}

/*Function that filters words from databse
*@param : data- user input
*@param :mysqli-database
*retrun filtered comments
*/
function filter_comment($data,$mysqli) {
    
    $res=mysqli_query($mysqli,"SELECT * FROM `REPLACE` WHERE 1") or die(mysqli_error($mysqli));
    while($row = mysqli_fetch_array($res))
    {
    	if(strcmp($data,$row['WORD'])==0){
    		$data=$row['CHANGE'];
    		break;
    	}
    }
    return $data;
}

?>