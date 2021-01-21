<template>
  <nav id="nav">
    <div class="navigation__nav">nav 1</div>
    <div class="navigation__nav">nav 2</div>
    <div class="navigation__nav">nav 3</div>
    <div class="navigation__nav">nav 4</div>
  </nav>

</template>

<script>
export default {
  name: 'HeaderNav',
};
</script>

<style lang="scss" scoped>

nav {
  grid-column: 1 / 4;
  display: flex;
  justify-content: space-around;
}

</style>
