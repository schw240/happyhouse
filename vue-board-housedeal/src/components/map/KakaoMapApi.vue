<template>
  <div>
    <b-container class="bv-example-row mt-3 text-center">
      <b-row>
        <b-col>
          <home-search-bar></home-search-bar>
        </b-col>
      </b-row>
    </b-container>
    <div class="map_wrap">
      <div
        id="map"
        v-if="houses || houses.length == 0"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      ></div>
    </div>
  </div>
</template>

<script>
// 카카오 맵에서 vuex에 있는 데이터를 가지고 와야함
// 데이터 가지고 검색하기 + 맵 위치 이동
import { mapState } from "vuex";
import HomeSearchBar from "@/components/map/HomeSearchBar.vue";
import { VUE_APP_KAKAO_KEY } from "@/config/index.js";

const homemapStore = "homemapStore";

export default {
  name: "KakaoMapApi",
  components: {
    HomeSearchBar,
  },
  // house로 데이터는 불러왔는데 이제 이거를 어떻게 해야하냐
  data() {
    return {
      // map: null,
      // markers: [],
      // infowindow: null,
      // customOverlay: null,
      sidoCode: null,
      gugunCode: null,
      key: null,
    };
  },
  // props: {
  //   house: Object,
  // },
  computed: {
    // 여기서 집 값의 데이터가 바뀔때마다 데이터를 가져와서
    // 카카오 맵을 다시 새로 그려줘야함
    ...mapState(homemapStore, ["houses", "sidos", "guguns", "keyword"]),
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${VUE_APP_KAKAO_KEY}&libraries=services`;
      document.head.appendChild(script);
    }
  },
  updated() {
    console.log("updated에서 호출!");
    console.log("updated에서 호출하는 구군", this.guguns);
    console.log("updated에서 호출하는 구군안 집들", this.houses);
    console.log("homemap 안 키워드", this.keyword.value);

    var key2 = this.keyword.value;

    var geocoder = new kakao.maps.services.Geocoder();
    var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    // var mapTypeControl = new kakao.maps.MapTypeControl();

    // 지도 타입 컨트롤을 지도에 표시합니다
    // map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    var mapContainer = document.getElementById("map");
    var mapOption = {
      //   center: new kakao.maps.LatLng(coords),
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 5,
    };
    // this.map = new kakao.maps.Map(container, options);
    var map = new kakao.maps.Map(mapContainer, mapOption);
    var ps = new kakao.maps.services.Places();
    console.log("houses 데이터 확인: ", this.houses);
    var add1 = this.houses[0].법정동;
    var add2 = this.houses[0].지번;
    console.log("가져온 주소: ", add1, add2);
    console.log("keyword!!!!: ", this.keyword);
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(add1 + add2, function (result, status) {
      // 정상적으로 검색이 완료됐으면
      if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        console.log("검색한 위치 정보", result[0].y, result[0].x);
        // // 결과값으로 받은 위치를 마커로 표시합니다
        // var marker = new kakao.maps.Marker({
        //   map: map,
        //   position: coords,
        // });
        const lat = result[0].y;
        const lng = result[0].x;
        console.log("위도 경도 정보: ", lat, lng);
        // this.map = new kakao.maps.Map(container, options);

        console.log("검색전 keyword: ", key2);
        // 키워드로 장소를 검색합니다
        ps.keywordSearch(key2, placesSearchCB, {
          location: new kakao.maps.LatLng(lat, lng),
          // page: 45,
        });
        // map.setLevel(5, { anchor: new kakao.maps.LatLng(lat, lng) });
        // map.setCenter(new kakao.maps.LatLng(coords));
        // map = new kakao.maps.Map(mapContainer, mapOption);

        var marker = new kakao.maps.Marker({
          map: map,
          position: coords,
        });
        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
          content:
            '<div style="width:150px;text-align:center;padding:6px 0;">검색 위치 중심</div>',
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      }
    });

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    function placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new kakao.maps.LatLngBounds();
        console.log("찾은 데이터", data);
        for (var i = 0; i < data.length; i++) {
          displayMarker(data[i]);
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
      }
    }
    // 지도에 마커를 표시하는 함수입니다
    function displayMarker(place) {
      // 마커를 생성하고 지도에 표시합니다
      var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
      });

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "click", function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent(
          '<div id="placeinfo">' +
            '   <a class="title" href="' +
            place.place_url +
            '" target="_blank" title="' +
            '">' +
            place.place_name +
            "</a>" +
            '    <span title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            "</span>" +
            '  <span class="jibun" title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ")</span>"
        );
        infowindow.open(map, marker);
      });
    }
  },
  methods: {
    initMap() {
      // const lat = this.house.lat;
      // const lng = this.house.lng;
      // console.log("위도 경도", lat, lng);
      const container = document.getElementById("map");
      const options = {
        // center: new kakao.maps.LatLng(33.450701, 126.570667),
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
    },
  },
};
</script>

<style>
#map {
  width: 70%;
  height: 300px;
  margin: auto;
}
.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
#placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 500px;
}
#placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
#placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
#placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
#placeinfo a,
#placeinfo a:hover,
#placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
#placeinfo a,
#placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
#placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
#placeinfo .tel {
  color: #0f7833;
}
#placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
