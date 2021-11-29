import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/news`, { params: param }).then(success).catch(fail);
}

function getArticle(title, success, fail) {
  api.get(`/news/${title}`).then(success).catch(fail);
}

export { listArticle, getArticle };
