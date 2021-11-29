<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-center">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <cat-dog-list-row
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            ></cat-dog-list-row>
            <!-- <cat-dog-list-row /> -->
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import CatDogListRow from "@/components/catdog/child/CatDogListRow";
import { listArticle } from "@/api/catdog.js";

export default {
  name: "CatDogList",
  components: {
    CatDogListRow,
  },
  data() {
    return {
      articles: [],
    };
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
        console.log("캣독 리스트 데이터", response.data);
        this.articles = response.data.articles;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "CatDogWrite" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
