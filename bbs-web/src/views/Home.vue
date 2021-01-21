<template>
  <div class="home">
    홈 화면
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  name: 'Home',
  setup() {
    const aa = ref('');

    return {
      aa,
    };
  },
};
</script>
