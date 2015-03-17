<!DOCTYPE html>
<html>
<head>
		<link type="text/css" rel="stylesheet" href="stylesheet.css"/>
		<title></title>
	</head>
<body>

<h2>Welcome to CS242!</h2>

<?php
//Initialize the XML parser
$entry = simplexml_load_file('svn_log.xml');
$listentry = simplexml_load_file('svn_list.xml');

$revison_arr=array();
$author_arr=array();
$date_arr=array();
$msg_arr=array();
$paths_arr=array();
$actions_arr=array();
$kinds_arr=array();
$files_size_arr=array();
//print_r($listentry);

foreach ($entry as $entryinfo){
	$revison=$entryinfo['revision'];
	$author=$entryinfo->author;
	$date=$entryinfo->date;
	$mssg=$entryinfo->msg;
	$paths=$entryinfo->paths;
	$path_arr=array();
	$action_arr=array();
	$kind_arr=array();
	$file_size=array();
	
	foreach ($paths->path as $pathinfo){
		//echo $pathinfo," " ,$pathinfo['kind']," ",$pathinfo['action'],"<br>";
		$path_arr[]=$pathinfo;
		$action_arr[]=$pathinfo['action'];
		$kind_arr[]=$pathinfo['kind'];
		foreach ($listentry as $listinfo){
			$path=$listinfo['path'];
			foreach($listinfo->entry as $field){
				$kind=$field['kind'];
				$commit=$field->commit['revision'];
				$name=$field->name;
				$token="/dgupta13/".$name;
				//echo "$token <br> $pathinfo";
				if(strcmp($token,$pathinfo)==0
					 && strcmp($commit,$revison)==0){
					if(strcmp($kind,'file')==0)
						$size=$field->size;
					else
						$size=0;	
					$file_size[]=$size;		
					//echo "size $name, $kind, $commit,$size <br>";
					break;
				}
				
			}
		}	
	}
	
	$revison_arr[]= $revison;
	$author_arr[]=$author;
	$date_arr[]=$date;
	$msg_arr[]=$mssg;
	$paths_arr[]=$path_arr;
	$kinds_arr[]=$kind_arr;
	$actions_arr[]=$action_arr;
	$files_size_arr[]=$file_size;
	
}

for($i=0;$i<count($revison_arr);$i++){	
	
	echo "<h3 class='textox'>Author $author_arr[$i]</h3>";
	echo "<h3 class='textox'>Revision $revison_arr[$i]</h3>";
	echo "<h3 class='textox'> TimeStamp $date_arr[$i]</h3>";
	echo "<div class='CSSTableGenerator' >";
	echo "<table border='1' style='width:100%' class='table table-hover'>";
	echo "<tr>";
		echo"<td>FILENAME</td>";
    		echo"<td>OPERATION</td>";		
   		echo"<td>TYPE</td>";
   		echo"<td>SIZE</td>";
  	echo "</tr>";
  	$file_name=$paths_arr[$i];
  	$file_action=$actions_arr[$i];
  	$file_kind=$kinds_arr[$i];
  	$file_size=$files_size_arr[$i];
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
}
?>


</body>
</html>