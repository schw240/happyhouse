<template>
  <b-container class="bv-example-row mt-3 text-center" fluid="md">
    <h3 class="underline-steelblue" style="margin-bottom: 30px">
      <b-icon icon="house"></b-icon> HAPPY HOUSE
    </h3>
    <b-row>
      <b-col cols="1"></b-col>
      <b-col cols="10">
        <b-carousel
          controls
          indicators
          v-model="slide"
          :interval="2500"
          background="#e1e1e1"
          img-width="1024px"
          img-height="300px"
          @sliding-end="endSlide"
          @sliding-start="initCarousel"
          style="text-shadow: 2px 3px 3px #222222"
        >
          <b-carousel-slide
            :img-src="
              require(`C:/Users/82108/갈매기/happyhouse_final_4-_-_/happyhouse_4조_김동규_김한주_이재영/src/main/webapp/resources/img/${this.articles[0].fileInfos[0].saveFolder}/${this.articles[0].fileInfos[0].saveFile}`)
            "
            id="catdogCarousel"
          >
          </b-carousel-slide>
          <b-carousel-slide
            :img-src="
              require(`C:/Users/82108/갈매기/happyhouse_final_4-_-_/happyhouse_4조_김동규_김한주_이재영/src/main/webapp/resources/img/${this.articles[1].fileInfos[0].saveFolder}/${this.articles[1].fileInfos[0].saveFile}`)
            "
            id="catdogCarousel"
          >
          </b-carousel-slide>
          <b-carousel-slide
            :img-src="
              require(`C:/Users/82108/갈매기/happyhouse_final_4-_-_/happyhouse_4조_김동규_김한주_이재영/src/main/webapp/resources/img/${this.articles[2].fileInfos[0].saveFolder}/${this.articles[2].fileInfos[0].saveFile}`)
            "
            id="catdogCarousel"
          >
          </b-carousel-slide>
          <b-carousel-slide
            :img-src="
              require(`C:/Users/82108/갈매기/happyhouse_final_4-_-_/happyhouse_4조_김동규_김한주_이재영/src/main/webapp/resources/img/${this.articles[3].fileInfos[0].saveFolder}/${this.articles[3].fileInfos[0].saveFile}`)
            "
            id="catdogCarousel"
          >
          </b-carousel-slide>
          <b-carousel-slide
            :img-src="
              require(`C:/Users/82108/갈매기/happyhouse_final_4-_-_/happyhouse_4조_김동규_김한주_이재영/src/main/webapp/resources/img/${this.articles[4].fileInfos[0].saveFolder}/${this.articles[4].fileInfos[0].saveFile}`)
            "
            id="catdogCarousel"
          >
          </b-carousel-slide>
        </b-carousel>
      </b-col>
      <b-col cols="1"></b-col>
    </b-row>
    <b-row align-h="around">
      <b-col cols="6" class="float right">
        <news-list></news-list>
      </b-col>
      <b-col cols="6">
        <board-list></board-list>
      </b-col>
    </b-row>
    <router-view></router-view>
  </b-container>
</template>

<script>
import NewsList from "../components/news/NewsList.vue";
import BoardList from "../components/board/BoardList.vue";
import { listArticle } from "@/api/catdog.js";

export default {
  name: "Home",
  components: {
    NewsList,
    BoardList,
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.articles = response.data.articles;
        console.log(
          "홈에서 보는 캣독 리스트 데이터",
          this.articles[0].fileInfos[0].saveFolder,
          this.articles[0].fileInfos[0].saveFile
        );
      },
      (error) => {
        console.log(error);
      }
    );
  },
  data() {
    return {
      slide: 0,
      sliding: null,
      articles: [],
    };
  },
  props: {
    msg: String,
  },
  methods: {
    initCarousel() {
      this.sliding = true;
    },
    endSlide() {
      this.sliding = false;
    },
  },
};
</script>

<style>
#catdogCarousel img {
  height: 500px;
  object-fit: fill;
}
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
