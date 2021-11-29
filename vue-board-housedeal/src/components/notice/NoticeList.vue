<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button
          variant="outline-primary"
          @click="moveWrite()"
          v-if="userInfo && userInfo.userid == 'admin'"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <notice-list-row
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
    <b-button size="sm" variant="outline-dark" @click="prev()" v-if="pg != 1"
      >이전</b-button
    >
    <span v-for="(page, index) in pages" :key="index">
      <b-button
        size="sm"
        variant="danger"
        v-if="page <= last && page == pg"
        @click="pageChange(page)"
      >
        {{ page }}
      </b-button>
      <b-button
        size="sm"
        v-if="page <= last && page != pg"
        @click="pageChange(page)"
        >{{ page }}</b-button
      >
    </span>
    <b-button size="sm" variant="outline-dark" @click="next()" v-if="pg != last"
      >다음</b-button
    >
  </b-container>
</template>

<script>
import NoticeListRow from "@/components/notice/child/NoticeListRow";
import { listArticle, totalArticle } from "@/api/notice.js";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "NoticeList",
  components: {
    NoticeListRow,
  },
  data() {
    return {
      articles: [],
      pages: [],
      pg: 1,
      spp: 10,
      start: 1,
      end: this.start + this.spp - 1,
      last: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let param = {
      pg: 1,
      spp: 10,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    totalArticle(param, (response) => {
      console.log(response);
      this.last = response.data;
      let pages = [];
      let start = (this.pg - 1) / 5 + 1;
      for (let i = start; i < start + 5; i++) {
        pages.push(i);
      }
      this.pages = pages;
    });
  },
  methods: {
    makePagenation() {
      let pages = [];
      let mod = this.pg % 5;
      if (mod == 0) mod = 5;
      let start = this.pg - mod + 1;
      for (let i = start; i < start + 5; i++) {
        pages.push(parseInt(i));
      }
      this.pages = pages;
    },
    moveWrite() {
      this.$router.push({ name: "NoticeWrite" });
    },
    next() {
      this.start += this.spp;
      let param = {
        pg: ++this.pg,
        spp: this.spp,
        start: this.start,
        key: null,
        word: null,
      };
      listArticle(
        param,
        (response) => {
          this.articles = response.data;
          this.makePagenation();
          console.log(this.pg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    prev() {
      this.start -= this.spp;
      let param = {
        pg: --this.pg,
        spp: this.spp,
        start: this.start,
        key: null,
        word: null,
      };
      listArticle(
        param,
        (response) => {
          this.articles = response.data;
          this.makePagenation();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    pageChange(page) {
      this.start = this.spp * (page - 1) + 1;
      this.pg = page;
      console.log(this.pg);
      let param = {
        pg: page,
        spp: this.spp,
        start: this.start,
        key: null,
        word: null,
      };
      listArticle(
        param,
        (response) => {
          this.articles = response.data;
          this.makePagenation();
        },
        (error) => {
          console.log(error);
        }
      );
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
