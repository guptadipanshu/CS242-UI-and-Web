<!DOCTYPE html>
<html lang="en">
<!-- Below Lines Bootstrap a template from http://getbootstrap.com/examples/dashboard/-->
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard Template for cs242</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="dashboard.css" rel="stylesheet">

   
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">CS242</a>
        </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview</a></li>
            <li><a href="#">Reports</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">SVN LOG</h1>
<!--Bootstrap complete-->          
<?php
include 'parser.php';
class Printer {
 protected $mysqli;
 protected $revison_arr=array();
 protected $author_arr=array();
 protected $date_arr=array();
 protected $msg_arr=array();
 protected $paths_arr=array();
 protected $actions_arr=array();
 protected $kinds_arr=array();
 protected $files_size_arr=array();
 
 public function __construct( /*...*/ ) {
 	//connect to the database
 	$this->mysqli= mysqli_connect('engr-cpanel-mysql.engr.illinois.edu','dgupta13_test','asd123','dgupta13_MYTEST');
 }
 
// Intialize a Model Parser that will parse and return the data
 public function get_data(){
 	
  	$parser = new Parser();
  	$parser->read_data();
  	$this->revison_arr=$parser->get_revision_arr();
  	$this->author_arr=$parser->get_author_arr();
  	$this->date_arr=$parser->get_date_arr();
  	$this->msg_arr=$parser->get_msg_arr();
  	$this->paths_arr=$parser->get_paths_arr();
  	$this->actions_arr=$parser->get_actions_arr();
  	$this->kinds_arr=$parser->get_kinds_arr();
  	$this->files_size_arr=$parser->get_files_size_arr();
 }
 //Function to print the view
 public function print_data(){
 //create 1-Table for every revision number
 	for($i=0;$i<count($this->revison_arr);$i++){	
  		$name=$this->author_arr[$i];
  		$revison= $this->revison_arr[$i];
		$msg=$this->msg_arr[$i];
		$time=$this->date_arr[$i];
		$curr_rev=$this->revison_arr[$i];
	 	
	 	mysqli_query($this->mysqli,"DELETE FROM TEST WHERE UID='0' AND CHILD='$curr_rev' ");
		mysqli_query($this->mysqli,"INSERT INTO `dgupta13_MYTEST`.`TEST` (`UID`, `NAME`, `COMMENT`, `CHILD`,`REPLY`) VALUES ('0', 'test', 'comment', '$curr_rev','0')");
		
		//call a function to print header
		$this->print_header($name,$revison,$msg,$time);
		//start print the table contents
		echo "<div class='table-responsive' >";
		echo "<table border='1' style='width:100%' class='table table-striped'>";
		echo "<tr>";
			echo"<td>FILENAME</td>";
    			echo"<td>OPERATION</td>";		
   			echo"<td>TYPE</td>";
   			echo"<td>SIZE</td>";
  		echo "</tr>";
  		$file_name=$this->paths_arr[$i];
  		$file_action=$this->actions_arr[$i];
  		$file_kind=$this->kinds_arr[$i];
  		$file_size=$this->files_size_arr[$i];
  		for($j=0;$j<count($file_name);$j++){
  			echo "<tr>";
    				echo"<td>$file_name[$j]</td>";	
    				echo"<td>$file_action[$j]</td>";	
    				echo"<td>$file_kind[$j]</td>";
    				if($file_size[$j]>0)
    					echo"<td>$file_size[$j]</td>";
    				else
    					echo"<td>No record</td>";
    				
  			echo"</tr>";
  		}
 		echo"</table><br>";
 		echo"</div>";
 		//function to support comment print
 		$this->print_comments($curr_rev);
 		
  	}
  	mysql_close($this->mysqli);
 }
 /* print the comments from database and intialize the form
  * @param:curr_rev revision number of the table
  */
  public function print_comments($curr_rev){ 
	$res=mysqli_query($this->mysqli,"SELECT * FROM TEST WHERE CHILD='$curr_rev' AND UID!='0'");
 	echo "<div class='dark-matter'>";
 	while($row = mysqli_fetch_array($res))
 	{
 	
   		echo "<h5> MESSSAGE ".$row['COMMENT']. " BY ".$row['NAME']."</h5>";
   		if($row['REPLY']==0){
    			echo "<br><form method='post' action='submit.php' class='dark-matter'>";
 			echo " <input type='text' name='name' value='name' />";
  			echo "<input type='text' name='comment' value='Reply Thread' />";
  			echo "<input type='hidden' name='revision' value=$curr_rev />";
  			echo "<input type='hidden' name='reply' value='1' />";
  			echo "<input type='submit' />";
   			echo "</form>";
   		}
    	
 	}
 	echo "<br><br>";
 	echo "<form method='post' action='submit.php' class='dark-matter' >";
  	echo " <input type='text' name='name' value='name' />";
  	echo "<input type='text' name='comment' value='New Thread' />";
  	echo "<input type='hidden' name='revision' value=$curr_rev />";
  	echo "<input type='hidden' name='reply' value='0' />";
  	echo "<input type='submit' />";
	echo "<br></form>";
	echo "</div>";
}
/**
*Function to print table header
*@param :name- name of the author
*@param :revision-revision number of commit
*@msg	:msg that was written during commit
*@time	:timestamp
**/
 public function print_header($name,$revision,$msg,$time){
	echo " <div class='row placeholders'>";
        echo" <div class='col-xs-6 col-sm-3 placeholder'>";
        echo" <img src='http://intelligentdiscontent.com/wp-content/uploads/2013/05/red-line.png' class='img-responsive' alt='Generic placeholder thumbnail'>";
        echo"   <h4>$name</h4>";
        echo"<span class='text-muted'>Revision $revision</span>";
        echo"</div>";
        echo" <div class='col-xs-6 col-sm-3 placeholder'>";
        echo" <img src='http://intelligentdiscontent.com/wp-content/uploads/2013/05/red-line.png' class='img-responsive' alt='Generic placeholder thumbnail'>";
        echo" <h4>Msg $msg</h4>";
        echo"<span class='text-muted'>Time $time</span>";
        echo"</div>";
        echo" <div class='col-xs-6 col-sm-3 placeholder'>";
        echo" <img src='http://intelligentdiscontent.com/wp-content/uploads/2013/05/red-line.png' class='img-responsive' alt='Generic placeholder thumbnail'>";
        echo"</div>";
        echo" <div class='col-xs-6 col-sm-3 placeholder'>";
        echo" <img src='http://intelligentdiscontent.com/wp-content/uploads/2013/05/red-line.png' class='img-responsive' alt='Generic placeholder thumbnail'>";
        echo"</div>";
        echo"</div>";

 }  
}
?>
<!bootstrap-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>