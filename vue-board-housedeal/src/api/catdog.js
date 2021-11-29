import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/catdog/list`, { params: param }).then(success).catch(fail);
}

function listFile(param, success, fail) {
  api.get(`/catdog/listfile`, { params: param }).then(success).catch(fail);
}

function writeArticle(formData, success, fail) {
  api.post(`/catdog/register`, formData).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/catdog/${articleno}`).then(success).catch(fail);
}

function getFile(articleno, success, fail) {
  api.get(`/catdog/file/${articleno}`).then(success).catch(fail);
}

function modifyArticle(formData, success, fail) {
  api.put(`/catdog`, formData).then(success).catch(fail);
}

function modifyFile(article, success, fail) {
  api.put(`/catdog/file`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/catdog//${articleno}`).then(success).catch(fail);
}

export {
  listArticle,
  writeArticle,
  getFile,
  listFile,
  getArticle,
  modifyArticle,
  modifyFile,
  deleteArticle,
};
