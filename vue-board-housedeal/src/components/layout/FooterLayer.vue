<template>
  <div>
    <footer class="bg-other">
      <div class="container secondContainer">
        <div class="row text-white text-center p-5">
          <div class="col-sm">
            <h3 style="margin-top: 13px">주소</h3>
            <p>
              <b-icon icon="geo-alt-fill" font-scale="1"></b-icon>
              <a
                class="text-white text-decoration-none"
                target="_blank"
                rel="noreferrer noopener"
                href="https://www.google.com/maps/place/%EB%A9%80%ED%8B%B0%EC%BA%A0%ED%8D%BC%EC%8A%A4+%EC%97%AD%EC%82%BC/@37.501311,127.037471,17z/data=!3m1!4b1!4m5!3m4!1s0x357ca3ff67128961:0x55a56e8ffc5bc5d!8m2!3d37.5013068!4d127.0396597"
              >
                (SSAFY) <br />
                서울시 강남구 테헤란로 멀티스퀘어</a
              >
            </p>
          </div>
          <div class="col-sm">
            <h4 style="margin-bottom: 15px">이메일문의</h4>
            <address>
              <p class="text-break">
                <b-icon icon="mailbox" font-scale="1"></b-icon>
                <a
                  class="text-white ms-2"
                  target="_blank"
                  rel="noreferrer noopener"
                  href="mailto:HappyHouse@gmail.com?subject=해피하우스 관련 문의 있어서 연락드립니다.&body=[이름]%0D%0A[연락처]%0D%0A*해피하우스측은 본 메일의 정보를 회원간의 원활한 의사소통만을 목적으로 하며, 다른 어떠한 목적으로도 외부에 유출되지 않습니다."
                >
                  HappyHouse@gmail.com</a
                >
              </p>
            </address>
            <h4 style="margin: 20px 0px">전화문의</h4>
            <p>
              <b-icon icon="telephone-fill" font-scale="1"></b-icon>
              <a
                rel="noreferrer noopener"
                href="tel:+820234295100"
                class="text-white"
              >
                (+82) 02 3429 5100</a
              >
            </p>
          </div>
          <div class="col-sm">
            <h3 style="margin-bottom: 15px">문의 시간</h3>
            <b-icon
              icon="calendar-check"
              font-scale="2"
              style="margin-bottom: 15px"
            ></b-icon>
            <p>월 - 토 : 08:00 - 18:00</p>
            <p>일 : 09:00 - 18:00</p>
          </div>
        </div>
        <hr class="text-secondary" />
        <div class="container-fuiled">
          <!-- row -->
          <div class="row">
            <!-- copyright column -->
            <div class="col-sm">
              <div class="p-3 text-white text-center">
                <a
                  class="text-white text-decoration-none"
                  href="https://github.com/schw240"
                  >6기 서울 9반 김동규 이재영 김한주</a
                >
              </div>
            </div>
            <!-- links column -->
            <div class="col-sm text-white text-center fs-3 p-2">
              <div class="row">
                <!-- youtube link -->
                <div class="col-sm">
                  <a
                    class="text-white"
                    target="_blank"
                    rel="noreferrer noopener"
                    href="https://www.youtube.com/channel/UC_XI3ByFO1uZIIH-g-zJZiw"
                    ><b-icon icon="youtube" font-scale="2.5"></b-icon
                  ></a>
                </div>
                <!-- facebook column -->
                <div class="col-sm">
                  <a
                    class="text-white"
                    target="_blank"
                    rel="noreferrer noopener"
                    href="https://www.facebook.com/hellossafy/"
                    ><b-icon icon="facebook" font-scale="2.5"></b-icon
                  ></a>
                </div>
                <!-- github column -->
                <div class="col-sm">
                  <a
                    class="text-white"
                    target="_blank"
                    rel="noreferrer noopener"
                    href="https://github.com/topics/ssafy"
                  >
                    <b-icon icon="github" font-scale="2.5"></b-icon
                  ></a>
                </div>
              </div>
            </div>
            <!-- Privacy Policy link and Terms Condition link column -->
            <div class="col-sm p-3 text-white text-center">
              <div class="col">
                <a class="text-white pe-3">Privacy Policy </a>
                <a class="text-white ps-3"> Terms Condition</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: "Footer",
};
</script>

<style scoped>
footer {
  margin-top: 100px;
}
.bg-other {
  background-color: burlywood;
}
.secondContainer {
  padding: 10px 0px;
}
.text-secondary {
  height: 1px;
}
footer {
  width: 100%;
}
</style>
