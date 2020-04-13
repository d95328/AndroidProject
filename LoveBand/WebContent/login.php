<?php

  require_once 'DBconfig.php';

  $id = $_GET["id"];      // Android 에서 넘긴 파라메터 GET Or Post 방식
  $password = $_GET["password"];

  $sql = "SELECT * FROM USER WHERE ID = '$id' AND Password = '$password';";
    

  $result = mysqli_query($con,$sql);
            //조회한 쿼리 결과
  $num_rows = mysqli_num_rows($result);    //조회된 rows 갯수
  $result;
  if($num_rows == 1){                       //조회된 rows 가 1이면 회원이 존재한다는 것
    $result = array('result' => 'success');
  }else{
    $result = array('result' => 'error');
  }
  // json 으로 출력
  header('Content-type: application/json');

  echo json_encode($result);

  mysqli_free_result($result);
  mysqli_close();

  ?>
