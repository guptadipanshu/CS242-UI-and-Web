<?php
 class Parser{
 protected $revison_arr=array();
 protected $author_arr=array();
 protected $date_arr=array();
 protected $msg_arr=array();
 protected $paths_arr=array();
 protected $actions_arr=array();
 protected $kinds_arr=array();
 protected $files_size_arr=array();
 protected $entry,$listentry;
 protected $revision;
 protected $mysqli;
 /* Constructor intializes reads from the XML FILE*/
 public function __construct( /*...*/ ) {
        $this->entry = simplexml_load_file('svn_log.xml');
 	$this->listentry = simplexml_load_file('svn_list.xml');
 	$this->mysqli= mysqli_connect('engr-cpanel-mysql.engr.illinois.edu','dgupta13_test','asd123','dgupta13_MYTEST');
    }
 /*Start parsing the XML Files */ 
 public function read_data() {
	foreach ($this->entry as $entryinfo){
		
		$this->revision=$entryinfo['revision'];
		$author=$entryinfo->author;
		$date=$entryinfo->date;
		$mssg=$entryinfo->msg;
		$paths=$entryinfo->paths;
		$this->read_helper($paths);
		$this->revison_arr[]= $this->revision;	
		$this->author_arr[]=$author;
		$this->date_arr[]=$date;
		$this->msg_arr[]=$mssg;
		
	}
	
}
  /*Read helper to help get all file paths and file type*/  
  public function read_helper($paths){
  	$path_arr=array();
	$action_arr=array();
	$kind_arr=array();
	$file_size=array();
	foreach ($paths->path as $pathinfo){
		//echo $pathinfo," " ,$pathinfo['kind']," ",$pathinfo['action'],"<br>";
		$path_arr[]=$pathinfo;
		$action_arr[]=$pathinfo['action'];
		$kind_arr[]=$pathinfo['kind'];
		foreach ($this->listentry as $listinfo){
			$path=$listinfo['path'];
			$file_size=$this->read_file_type($listinfo,$pathinfo,$file_size);
 		 }
 	}
 	$this->paths_arr[]=$path_arr; 
 	$this->kinds_arr[]=$kind_arr;
 	$this->actions_arr[]=$action_arr;
 	$this->files_size_arr[]=$file_size;
 	
 }
  /*Reads the file size given the information about file path
  *@param listinfo -information about the list
  *@param pathinfo -information about file path
  *@param file_size-infromation about file size
  *return update file_size
  */
  public function read_file_type($listinfo,$pathinfo,$file_size){
  	foreach($listinfo->entry as $field){
		$kind=$field['kind'];
		$commit=$field->commit['revision'];
		$name=$field->name;
		$token="/dgupta13/".$name;
		//echo "$commit  r=$revision <br>";
		if(strcmp($token,$pathinfo)==0 && strcmp($commit,$this->revision)==0){
			if(strcmp($kind,'file')==0)
			{	$size=$field->size;
				
			}
			else
				$size=0;	
			$file_size[]=$size;		
			break;
		}
	}
	//print_r($file_size);
	return $file_size;
	
  }  
  
  /*Used in unit test to test parse*/ 
  
  //return array contating all author names
  public function get_author_arr()
  {
  	return $this->author_arr;	
  }
  /*return array contating all revisions*/
  public function get_revision_arr()
  {
  	return $this->revison_arr;	
  }
  /*return array contating all date*/
  public function get_date_arr()
  {
  	return $this->date_arr;	
  }
  /*return array contating all message*/
  public function get_msg_arr()
  {
  	return $this->msg_arr;	
  }
  /*return array contating all file path*/
  public function get_paths_arr()
  {
  	return $this->paths_arr;	
  }
  /*return array contating all actions on files*/
  public function get_actions_arr()
  {
  	return $this->actions_arr;	
  }
  /*return array contating all file types*/
  public function get_kinds_arr()
  {
  	return $this->kinds_arr;	
  }
  /*return array contating all file size*/
  public function get_files_size_arr()
  {
  	return $this->files_size_arr;	
  }
  
 }
?>