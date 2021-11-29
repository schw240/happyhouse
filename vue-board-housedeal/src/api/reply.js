import { apiInstance } from "./index.js";

const api = apiInstance();

function writeReply(reply, success, fail) {
  api.post(`/reply`, JSON.stringify(reply)).then(success).catch(fail);
}

function listReply(boardid, success, fail) {
  api.get(`/reply/${boardid}`).then(success).catch(fail);
}

function deleteReply(replyid, success, fail) {
  api.delete(`/reply/${replyid}`).then(success).catch(fail);
}
function updateReply(reply, success, fail) {
  api.put(`/reply/`, JSON.stringify(reply)).then(success).catch(fail);
}

export { writeReply, listReply, deleteReply, updateReply };
