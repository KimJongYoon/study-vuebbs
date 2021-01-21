<template>
  <div class="bbs-panel">
    <table class="bbs-panel__table">
      <thead>
      <tr>
        <th>NO</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일시</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(bbs, id) in bbsList.datas" :key="id">
        <td>{{ bbs.id }}</td>
        <td @click="goDetail(bbs.id)">{{ bbs.title }}</td>
        <td>{{ bbs.registId }}</td>
        <td>{{ bbs.registDtm }}</td>
      </tr>
      </tbody>
    </table>
    <div class="btn-panel">
      <router-link to="/bbs/new">
        <button type="button">글쓰기</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import { reactive, toRefs } from 'vue';
import { useRouter } from 'vue-router';
import * as bbsApi from '@/api/bbs/bbs';

/**
 * 게시판 관련 이벤트
 * @param routerParam
 * @returns {{goDetail(*): void}}
 */
function bbsEvent(routerParam) {
  const { router } = routerParam;

  return {
    goDetail(param) {
      router.push(`/bbs/${param}`); // router.push 활용법 1
      // router.push({ name: 'BbsDetail', params: { bbsId: param } }); // router.push 활용법 2
    },
  };
}

/**
 * 게시글 불러오기
 * @returns {{bbsList: UnwrapNestedRefs<{datas: []}>}}
 */
function getBbsDatas() {
  const bbsList = reactive({
    datas: [],
  });

  bbsApi.getBbs().then((res) => {
    bbsList.datas = res.data;
  }).catch((error) => {
    console.error(error);
  });

  return {
    bbsList,
  };
}

export default {
  name: 'Bbs',
  setup() {
    const { bbsList } = toRefs(getBbsDatas());
    const { goDetail } = bbsEvent({
      router: useRouter(),
    });

    return {
      bbsList,
      goDetail,
    };
  },
};
</script>

<style scoped>

</style>
