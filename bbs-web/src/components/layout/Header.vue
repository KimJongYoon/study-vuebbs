<template>
  <header id="header">
    <HeaderTop/>
    <HeaderNav/>
  </header>

</template>

<script>
import HeaderTop from '@/components/layout/HeaderTop.vue';
import HeaderNav from '@/components/layout/HeaderNav.vue';

export default {
  name: 'Header',
  components: { HeaderTop, HeaderNav },
};
</script>

<style lang="scss">

header {
  grid-column: 1 / 4;
  display: grid;
}

</style>
