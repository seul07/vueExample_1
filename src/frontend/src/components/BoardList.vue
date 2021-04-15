<template>
  <div class="container">
   <div id="app">
    <table border="1" style="border-collapse:collapse;">
        <tr v-for="index in list" v-bind:key="index">
            <td>{{list.boardSeq}}</td>
            <td>{{list.title}}</td>
            <td>{{list.contents}}</td>
            <td>{{list.regDate}}</td>
        </tr>
    </table>
  </div>
  </div>  
</template>
<script> 
 var example1 = new Vue({
   el : '#app', 
   data : {
     list : [{boardSeq:"1", title:"2",contents:"3",regDate:"4"}]
   } 
})
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
