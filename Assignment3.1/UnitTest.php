<?php
class DataTest extends PHPUnit_Framework_TestCase
{
   
    /*test sql injection*/
    public function testSlash()
    {
       $mysqli= mysqli_connect('engr-cpanel-mysql.engr.illinois.edu','dgupta13_test','asd123','dgupta13_MYTEST'); 
       $res=mysqli_query($mysqli,"SELECT * FROM TEST WHERE NAME='HACKER' ORDER BY UID DESC LIMIT 1");
       while($row = mysqli_fetch_array($res))
		{
     		$data=$row['COMMENT'];
		}
       $this->assertEquals('rn',$data);
    }

    /*Test scripts*/
    public function testScript()
    {
        $mysqli= mysqli_connect('engr-cpanel-mysql.engr.illinois.edu','dgupta13_test','asd123','dgupta13_MYTEST'); 
       $res=mysqli_query($mysqli,"SELECT * FROM TEST WHERE NAME='HACKER1' ORDER BY UID DESC LIMIT 1");
       while($row = mysqli_fetch_array($res))
		{
     		$data=$row['COMMENT'];
		}
       $this->assertEquals('<script>&?',$data);
    }
	/*Test Word Replace*/
    public function testReplace()
    {
	   $mysqli= mysqli_connect('engr-cpanel-mysql.engr.illinois.edu','dgupta13_test','asd123','dgupta13_MYTEST'); 
       $res=mysqli_query($mysqli,"SELECT * FROM TEST WHERE NAME='REPLACE' ORDER BY UID DESC LIMIT 1");
       while($row = mysqli_fetch_array($res))
		{
     		$data=$row['COMMENT'];
		}
       $this->assertEquals('THIS IS',$data);
    }
    /*Test No word Replace*/
    public function testReplace()
    {
	   $mysqli= mysqli_connect('engr-cpanel-mysql.engr.illinois.edu','dgupta13_test','asd123','dgupta13_MYTEST'); 
       $res=mysqli_query($mysqli,"SELECT * FROM TEST WHERE NAME='REPLACE2' ORDER BY UID DESC LIMIT 1");
       while($row = mysqli_fetch_array($res))
		{
     		$data=$row['COMMENT'];
		}
       $this->assertEquals('where',$data);
    }
}
?>
