<template>
  <aside class="sidebar-a">side a</aside>

  <ContentMain/>

  <aside class="sidebar-b">side b</aside>
</template>

<script>
import ContentMain from '@/components/layout/ContentMain.vue';

export default {
  name: 'Content',
  components: { ContentMain },
};
</script>

<style scoped>

</style>
