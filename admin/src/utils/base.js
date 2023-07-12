const base = {
    get() {
        return {
            url : "http://localhost:8080/server/",
            name: "server",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/server/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "web端音乐播放器"
        }
    }
}
export default base
