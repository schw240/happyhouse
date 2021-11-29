<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글수정</h3></b-alert>
      </b-col>
    </b-row>
    <cat-dog-write-form type="modify" />
  </b-container>
</template>

<script>
import CatDogWriteForm from "./child/CatDogWriteForm.vue";

export default {
  name: "CatDogUpdate",
  components: {
    CatDogWriteForm,
  },
};
</script>

<style></style>
