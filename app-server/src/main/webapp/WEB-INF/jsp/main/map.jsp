<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<!-- 1. 약도 노드 -->
<div id="daumRoughmapContainer1637139287479" class="root_daum_roughmap root_daum_roughmap_landing"></div>

<!-- 2. 설치 스크립트 -->
<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>

<!-- 3. 실행 스크립트 -->
<div id="map">
<script charset="UTF-8">
  new daum.roughmap.Lander({
    "timestamp" : "1637139287479",
    "key" : "28476",
    "mapWidth" : "1130",
    "mapHeight" : "460",
  }).render();
  </script>
</div>

</body>
</html>