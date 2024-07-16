import baseDefinition from '@gcpaas/data-room-ui/packages/components/G2Plots/BaseColumn/baseDefinition'

/**
 * 根据图表配置生成对应的实例，用于初始化
 */
function getInstance () {
  const chartDefinition = {
    ...baseDefinition,
    type: 'BaseColumn',
    option: {
      ...baseDefinition.option,
      color: ['rgba(1, 238, 255, 1)']
    },
    w: 6,
    h: 7,
    i: null // 组件拖拽唯一标识
  }
  // 每个组件实现自己的初始化逻辑
  return chartDefinition
}
export default getInstance
