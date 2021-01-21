import http from '../http';

export async function getBbs(bbsId) {
  if (bbsId === undefined) return http.get('/bbs');

  return http.get(`/bbs/${bbsId}`);
}

export async function addBbs(data) {
  return http.post('/bbs', data);
}

export default http;
