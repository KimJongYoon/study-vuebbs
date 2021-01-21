<template>
  <div class="bbs_detail">
    상세 화면
    <form>
      <label for="title">제목</label>
      <input type="text" id="title" v-model="bbsNewForm.title" />
      <label for="content">내용</label>
      <textarea rows="3" id="content" v-model="bbsNewForm.content"></textarea>
      <button type="button" @click="updateBbs">수정</button>
      <button type="button" @click="goList">목록</button>
    </form>
  </div>

</template>

<script>
import { reactive } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import * as bbsApi from '@/api/bbs/bbs';

function btnEvent(btnParam) {
  const { router, addData } = btnParam;
  return {
    goList() {
      router.push('/bbs');
    },
    updateBbs() {
      bbsApi.addBbs(addData).then(() => {
        router.push('/bbs');
      })
        .catch((error) => {
          console.error(error);
        });
    },
  };
}

export default {
  name: 'BbsDetail',
  setup() {
    const router = useRouter();
    const route = useRoute();
    const { bbsId } = route.params;
    console.log(bbsId);

    const bbsNewForm = reactive({
      title: '',
      content: '',
    });

    const btnParam = {
      router,
      addData: bbsNewForm,
    };

    const { goList, updateBbs } = btnEvent(btnParam);

    bbsNewForm.title = '123';

    console.log("함수 전 : ", bbsNewForm);
    console.log("함수 전 title: ", bbsNewForm.title);

    bbsApi.getBbs(bbsId).then((res) => {
      const { title, content } = res.data;
      bbsNewForm.title = title;
      bbsNewForm.content = content;
    }).catch((error) => {
      console.error(error);
    });
    return {
      bbsNewForm,
      goList,
      updateBbs,
    };
  },
};

</script>

<style scoped>
form {
  display: grid;
}
</style>
