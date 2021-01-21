<template>
  <div id="app">
    <Header/>
    <Content/>
    <Footer/>
  </div>
</template>

<script>
import Header from '@/components/layout/Header.vue';
import Content from '@/components/layout/Content.vue';
import Footer from '@/components/layout/Footer.vue';

export default {
  name: 'Layout',
  components: { Header, Content, Footer },
};
</script>

<style lang="scss" scoped>
#app {
  padding: 0 10%;
  display: grid;
  align-content: stretch;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-rows:  10vh minmax(80vh, auto) 1vh;
}
</style>
