<style lang="less" scoped>
.scrollWarp{
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  overflow-y: auto;
}
.listbox{
  position: relative;
}
.loadingbar{
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
}
.con{
  padding-bottom: 3rem;
}
.gray{
  color:#cacaca;
}
@-webkit-keyframes uil-default-anim {
    0% {
        opacity: 1
    }
    100% {
        opacity: 0
    }
}

@keyframes uil-default-anim {
    0% {
        opacity: 1
    }
    100% {
        opacity: 0
    }
}

.uil-default-css > div:nth-of-type(1) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: -0.5s;
    animation-delay: -0.5s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(2) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: -0.4166666666666667s;
    animation-delay: -0.4166666666666667s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(3) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: -0.33333333333333337s;
    animation-delay: -0.33333333333333337s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(4) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: -0.25s;
    animation-delay: -0.25s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(5) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: -0.16666666666666669s;
    animation-delay: -0.16666666666666669s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(6) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: -0.08333333333333331s;
    animation-delay: -0.08333333333333331s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(7) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: 0s;
    animation-delay: 0s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(8) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: 0.08333333333333337s;
    animation-delay: 0.08333333333333337s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(9) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: 0.16666666666666663s;
    animation-delay: 0.16666666666666663s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(10) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: 0.25s;
    animation-delay: 0.25s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(11) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: 0.33333333333333337s;
    animation-delay: 0.33333333333333337s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}

.uil-default-css > div:nth-of-type(12) {
    -webkit-animation: uil-default-anim 1s linear infinite;
    animation: uil-default-anim 1s linear infinite;
    -webkit-animation-delay: 0.41666666666666663s;
    animation-delay: 0.41666666666666663s;
}

.uil-default-css {
    position: relative;
    background: none;
    width: 3rem;
    height: 3rem;
}
.uil-right-text {
    line-height: 3rem;
    width: 3rem;
    height: 3rem;
}
.al-c{
  text-align: center;
}
.pdt3{
  padding-top: 3rem;
}
.fz-s{
  font-size: .8rem;
}
.pdt1{
  padding-top: 1rem;
}
//==clearfix

.clearfix:before,.clearfix:after{
    content:"";
    display:table;
}
.clearfix:after{clear:both;}

.show{
    display: block !important;
}
.hide{
    display: none !important;
}
</style>

<template>
  <div class="scrollWarp" :id="scrollname+'conlist'">
    <div class="listbox" :id="scrollname+'maincon'">
      <div class="con">
        <slot name="list">
          <div class="al-c pdt3 gray">暂无数据！</div>
        </slot>
        <div class="clearfix"  class="loadingbar pdt1" :class="showListLoading?'show':'hide'">
          <div>
            <div class='uil-default-css' style='margin:0 auto'>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(0deg) translate(0,-7.2px);transform:rotate(0deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(30deg) translate(0,-7.2px);transform:rotate(30deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(60deg) translate(0,-7.2px);transform:rotate(60deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(90deg) translate(0,-7.2px);transform:rotate(90deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(120deg) translate(0,-7.2px);transform:rotate(120deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(150deg) translate(0,-7.2px);transform:rotate(150deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(180deg) translate(0,-7.2px);transform:rotate(180deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(210deg) translate(0,-7.2px);transform:rotate(210deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(240deg) translate(0,-7.2px);transform:rotate(240deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(270deg) translate(0,-7.2px);transform:rotate(270deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(300deg) translate(0,-7.2px);transform:rotate(300deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
                <div style='top:9.2px;left:11.6px;width:2px;height:6px;background:#7d7d7d;-webkit-transform:rotate(330deg) translate(0,-7.2px);transform:rotate(330deg) translate(0,-7.2px);border-radius:10px;position:absolute;'></div>
            </div>
          </div>
        </div>
        <div class="al-c gray fz-s pdt1" :class="none?'show':'hide'">没有更多了</div>
      </div>
    </div>
  </div>
</template>

<script>
// import coerceBoolean from '../utils/coerceBoolean.js';
// import listloading from './listloading.vue';
export default {
  props: {
    currentpage: {
      type: Number,
      default: 1
    },
    pagesize: {
      type: Number,
      default: 6
    },
    totalsize: {
      type: Number,
      default: 10
    },
    scrollaction: {
      type: Function,
      default: () => false
    },
    scrollname: {
      type: String,
      default: 'scroll'
    }
  },
  data () {
    return {
      showListLoading: false,
      isLocked: false,
      isPageOver: false,
      none: false
    };
  },
  ready () {
    const domScroll = document.getElementById(this.scrollname + 'conlist');
    const maincon = document.getElementById(this.scrollname + 'maincon');

    domScroll.onscroll = () => {
      if (!this.isPageOver) { // 是否还有页面
        if (domScroll.scrollTop + domScroll.offsetHeight + 5 >= maincon.scrollHeight) { // 触底后执行
          if (!this.isLocked) { // 翻页过程中锁定翻页事件
            this.isLocked = true;
            this.showListLoading = true;
            this.scrollaction().then(
              () => {
                // 操作结束后解除锁定
                this.isLocked = false;
                this.showListLoading = false;
              }
            );
          }
          // 判断是否还能翻页
          if (this.totalsize <= (this.currentpage * this.pagesize)) {
            this.isPageOver = true;
            this.none = true;
          }
        }
      } else {
        setTimeout(() => {
          this.none = false;
        }, 3000);
      }
    };
  }
};
</script>
