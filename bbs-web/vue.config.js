// vue 실행 시 이 설정 파일을 먼저 찾아서 설정을 적용한다.

module.exports = {
    css: {
        loaderOptions: {
            sass: {
                additionalData: '@import "@/styles/base.scss";' // 전역설정 css 파일 적용
            }
        }
    }
}