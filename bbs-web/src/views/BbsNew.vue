<template>
  <div class="bbs_new">
    <form>
      <label for="title">제목</label>
      <input type="text" id="title" v-model="bbsNewForm.title" />
      <label for="content">내용</label>
      <textarea rows="3" id="content" v-model="bbsNewForm.content"></textarea>
      <button type="button" @click="addBbs">저장</button>
      <button type="button" @click="goList">목록</button>
    </form>
  </div>

</template>

<script>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import * as bbsApi from '@/api/bbs/bbs';

function btnEvent(btnParam) {
  const { router, addData } = btnParam;
  return {
    goList() {
      router.push('/bbs');
    },
    addBbs() {
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
  name: 'BbsNew',
  setup() {
    const router = useRouter();
    const bbsNewForm = reactive({
      title: '',
      content: '',
    });

    const btnParam = {
      router,
      addData: bbsNewForm,
    };

    const { goList, addBbs } = btnEvent(btnParam);

    return {
      bbsNewForm,
      goList,
      addBbs,
    };
  },
};

</script>

<style scoped>
form {
  display: grid;
}
</style>
