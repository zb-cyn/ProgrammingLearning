<template>
    <div>
        <!-- 完整窗口 -->
        <div class="ai-chat-window" v-show="!isMinimized"
            :style="{ left: position.x + 'px', top: position.y + 'px', width: size.width + 'px', height: size.height + 'px' }"
            @mousedown="startDrag">
            <!-- 窗口头部 -->
            <div class="window-header">
                <div class="title">AI编程咨询助手</div>
                <div class="controls">
                    <el-button type="text" @click="minimize" class="control-btn">
                        <i class="el-icon-minus"></i>
                    </el-button>
                    <el-button type="text" @click="$emit('close')" class="control-btn">
                        <i class="el-icon-close"></i>
                    </el-button>
                </div>
            </div>

            <!-- 聊天内容区域 -->
            <div class="chat-content">
                <div class="message-list">
                    <div class="ai-welcome">
                        <div class="header">
                            <img :src="require('@/assets/智能AI.png')" class="headImg" />
                            <span class="name">智能机器人</span>
                        </div>
                        <div class="ai-content">我是智能AI，请对我说您的问题</div>
                    </div>

                    <div v-for="(item, index) in MessageList" :key="index"
                        :class="['message-item', item.type === 'ai' ? 'ai-message' : 'user-message']">
                        <div class="message-header">
                            <img :src="item.type === 'ai' ? require('@/assets/智能AI.png') : require('@/assets/head.jpg')"
                                class="headImg" />
                            <span class="name">{{ item.type === 'ai' ? '智能机器人' : '我' }}</span>
                            <span class="time">{{ item.time }}</span>
                        </div>
                        <div class="message-content">{{ item.content }}</div>
                    </div>
                </div>
            </div>

            <!-- 输入区域 -->
            <div class="chat-input">
                <textarea v-model="Message" :disabled="Status === 'ttsing'" placeholder="请输入内容" rows="3"></textarea>
                <el-button type="primary" @click="SendInfo()">
                    {{ Status === 'ttsing' ? '发送中' : '发送内容' }}
                </el-button>
            </div>

            <!-- 添加调整大小的把手 -->
            <div class="resize-handle resize-handle-right" @mousedown.stop="startResize('right')"></div>
            <div class="resize-handle resize-handle-bottom" @mousedown.stop="startResize('bottom')"></div>
            <div class="resize-handle resize-handle-corner" @mousedown.stop="startResize('corner')"></div>
        </div>

        <!-- 最小化后的图标 -->
        <div class="minimized-icon" v-show="isMinimized" :style="{ left: position.x + 'px', top: position.y + 'px' }"
            @mousedown="startDrag">
            <img :src="require('@/assets/智能AI.png')" alt="AI助手" />
        </div>
    </div>
</template>

<script>
import TTSRecorder from "@/utils/spark/sparkwebscoket.js";
import { mapGetters } from 'vuex';

export default {
    name: "AIChatWindow",
    props: {
        visible: {
            type: Boolean,
            default: false
        }
    },
    computed: {
        ...mapGetters([
            'Token', 'UserInfo', 'RoleType', 'HasUserInfo', 'ColumnType', "UserId"
        ])
    },
    data() {
        return {
            position: {
                x: 100,
                y: 100
            },
            isDragging: false,
            dragOffset: {
                x: 0,
                y: 0
            },
            bigModel: null,
            MessageList: [],
            Message: "",
            Status: "",
            size: {
                width: 400,
                height: 600
            },
            isResizing: false,
            resizeType: null,
            minSize: {
                width: 300,
                height: 400
            },
            maxSize: {
                width: 800,
                height: 800
            },
            isMinimized: true,
            // 保存最小化前的位置和尺寸
            previousState: null,
            clickStartTime: 0,
            clickStartPosition: { x: 0, y: 0 }
        }
    },
    created() {
        this.initAI();
        // 添加全局鼠标事件监听
        document.addEventListener('mousemove', this.onDrag);
        document.addEventListener('mouseup', this.stopDrag);
    },
    beforeDestroy() {
        // 清理事件监听
        document.removeEventListener('mousemove', this.onDrag);
        document.removeEventListener('mouseup', this.stopDrag);
    },
    methods: {
        
        // 拖拽相关方法
        startDrag(e) {
            if (e.target.closest('.window-header') || e.target.closest('.minimized-icon')) {
                e.preventDefault();
                this.isDragging = true;
                this.dragOffset.x = e.clientX - this.position.x;
                this.dragOffset.y = e.clientY - this.position.y;

                // 记录点击开始时间和位置
                this.clickStartTime = Date.now();
                this.clickStartPosition = {
                    x: e.clientX,
                    y: e.clientY
                };
            }
        },
        onDrag(e) {
            if (this.isDragging) {
                e.preventDefault(); // 防止文本选择
                // 计算新位置
                let newX = e.clientX - this.dragOffset.x;
                let newY = e.clientY - this.dragOffset.y;

                // 获取窗口尺寸
                const windowWidth = window.innerWidth;
                const windowHeight = window.innerHeight;

                // 根据当前状态获取正确的宽度和高度
                const elementWidth = this.isMinimized ? 50 : this.size.width;
                const elementHeight = this.isMinimized ? 50 : this.size.height;

                // 限制 X 轴范围
                newX = Math.max(0, Math.min(windowWidth - elementWidth, newX));
                // 限制 Y 轴范围
                newY = Math.max(0, Math.min(windowHeight - elementHeight, newY));

                // 更新位置
                this.position.x = newX;
                this.position.y = newY;
            }
        },
        stopDrag(e) {
            if (this.isDragging) {
                const endTime = Date.now();
                const moveDistance = Math.sqrt(
                    Math.pow(e.clientX - this.clickStartPosition.x, 2) +
                    Math.pow(e.clientY - this.clickStartPosition.y, 2)
                );

                // 如果是短时间内的小距离移动，认为是点击
                if (endTime - this.clickStartTime < 200 && moveDistance < 5) {
                    if (this.isMinimized) {
                        this.restore();
                    }
                }
            }
            this.isDragging = false;
        },
        // 原有的AI相关方法
        initAI() {
            this.bigModel = new TTSRecorder({
                MessageCallBack: this.MessageCallBack,
                OnWillStatusChange: this.OnWillStatusChange,
                InitMessage: "你好我是你的编程咨询AI智能助手"
            });
        },
        async SendInfo() {
            if (!this.Message) {
                this.$message.warning("请输入内容");
                return;
            }

            // 添加用户消息到列表
            this.MessageList.push({
                type: 'user',
                content: this.Message,
                time: new Date().toLocaleTimeString()
            });

            // 转换消息格式以适配 AI 模型
            const aiMessages = this.MessageList.map(msg => ({
                role: msg.type === 'ai' ? 'assistant' : 'user',
                content: msg.content
            }));

            // 发送消息到 AI 模型
            this.bigModel.SendMessage(aiMessages);
        },
        async OnWillStatusChange(oldstatus, newstatus) {
            this.Status = newstatus;
        },
        async MessageCallBack(jsonData) {
            const lastMessage = this.MessageList[this.MessageList.length - 1];

            if (lastMessage.type === 'user') {
                // 添加新的 AI 回复
                this.MessageList.push({
                    type: 'ai',
                    content: jsonData.payload.choices.text.map(x => x.content).join(''),
                    time: new Date().toLocaleTimeString()
                });
            } else {
                // 更新现有 AI 回复
                lastMessage.content += jsonData.payload.choices.text.map(x => x.content).join('');
            }

            // 消息完成时清空输入框
            if (jsonData.header.code === 0 && jsonData.header.status === 2) {
                this.Message = "";
            }
        },
        startResize(type) {
            this.isResizing = true;
            this.resizeType = type;
            document.addEventListener('mousemove', this.handleResize);
            document.addEventListener('mouseup', this.stopResize);
        },
        handleResize(e) {
            if (!this.isResizing) return;

            const rect = this.$el.getBoundingClientRect();
            const windowWidth = window.innerWidth;
            const windowHeight = window.innerHeight;

            if (this.resizeType === 'right' || this.resizeType === 'corner') {
                let newWidth = e.clientX - rect.left;
                newWidth = Math.min(Math.max(newWidth, this.minSize.width), this.maxSize.width);
                this.size.width = newWidth;
            }

            if (this.resizeType === 'bottom' || this.resizeType === 'corner') {
                let newHeight = e.clientY - rect.top;
                newHeight = Math.min(Math.max(newHeight, this.minSize.height), this.maxSize.height);
                this.size.height = newHeight;
            }
        },
        stopResize() {
            this.isResizing = false;
            this.resizeType = null;
            document.removeEventListener('mousemove', this.handleResize);
            document.removeEventListener('mouseup', this.stopResize);
        },
        minimize() {
            this.previousState = {
                position: { ...this.position },
                size: { ...this.size }
            };
            this.isMinimized = true;
        },
        restore() {
            if (this.previousState) {
                this.position = { ...this.previousState.position };
                this.size = { ...this.previousState.size };
            }
            this.isMinimized = false;
        }
    }
}
</script>

<style scoped lang="scss">
/* 聊天窗口基础样式 */
.ai-chat-window {
    position: fixed;
    width: 400px;
    min-height: 600px;
    background: #f8f9fa;
    border-radius: 12px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
    display: flex;
    flex-direction: column;
    z-index: 1000;
    overflow: hidden;

    /* 调整大小把手样式 */
    .resize-handle {
        position: absolute;
        background: transparent;

        &:hover {
            background: rgba(0, 0, 0, 0.1);
        }

        &.resize-handle-right {
            top: 0;
            right: 0;
            width: 4px;
            height: 100%;
            cursor: e-resize;
        }

        &.resize-handle-bottom {
            bottom: 0;
            left: 0;
            width: 100%;
            height: 4px;
            cursor: s-resize;
        }

        &.resize-handle-corner {
            right: 0;
            bottom: 0;
            width: 10px;
            height: 10px;
            cursor: se-resize;
        }
    }
}

/* 窗口头部样式 */
.window-header {
    padding: 16px;
    background: var(--primary-color);
    color: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
    cursor: move;
    user-select: none;

    .title {
        font-weight: 600;
        font-size: 16px;
    }

    .controls {
        display: flex;
        align-items: center;

        .control-btn {
            padding: 4px 8px;
            color: white;
            margin-left: 4px;

            &:hover {
                background: rgba(255, 255, 255, 0.1);
                border-radius: 4px;
            }

            i {
                font-size: 16px;
            }
        }
    }
}

/* 聊天内容区域 */
.chat-content {
    flex: 1;
    overflow-y: auto;
    padding: 20px;
    background: #fff;
    min-height: 200px;

    &::-webkit-scrollbar {
        width: 6px;
    }

    &::-webkit-scrollbar-thumb {
        background: #ddd;
        border-radius: 3px;
    }
}

/* 消息列表样式 */
.message-list {
    .message-item {
        margin-bottom: 24px;

        .message-header {
            display: flex;
            align-items: center;
            margin-bottom: 8px;

            .headImg {
                width: 36px;
                height: 36px;
                min-width: 36px;
                min-height: 36px;
                border-radius: 50%;
                object-fit: cover;
                border: 2px solid #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .name {
                margin-left: 12px;
                font-weight: 600;
                font-size: 14px;
                color: #333;
            }

            .time {
                margin-left: 8px;
                font-size: 12px;
                color: #999;
            }
        }

        .message-content {
            padding: 12px 16px;
            border-radius: 12px;
            position: relative;
            max-width: 85%;
            line-height: 1.5;
        }
    }

    .ai-message {
        .message-content {
            background-color: #f5f7fa;
            color: #333;
            margin-left: 48px;
            border-top-left-radius: 4px;
        }
    }

    .user-message {
        .message-header {
            flex-direction: row-reverse;

            .message-info {
                margin-left: 0;
                margin-right: 12px;
                text-align: right;
            }
        }

        .message-content {
            margin-left: auto;
            margin-right: 48px;
            background-color: var(--primary-color);
            color: white;
            border-top-right-radius: 4px;
        }
    }
}

/* AI欢迎消息样式 */
.ai-welcome {
    margin-bottom: 24px;

    .header {
        display: flex;
        align-items: center;
        margin-bottom: 8px;

        .headImg {
            width: 36px;
            height: 36px;
            min-width: 36px;
            min-height: 36px;
            border-radius: 50%;
            object-fit: cover;
            border: 2px solid #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .name {
            margin-left: 12px;
            font-weight: 600;
            font-size: 14px;
            color: #333;
        }
    }

    .ai-content {
        padding: 12px 16px;
        background-color: #f5f7fa;
        border-radius: 12px;
        border-top-left-radius: 4px;
        margin-left: 48px;
        color: #333;
        line-height: 1.5;
    }
}

/* 输入区域 */
.chat-input {
    padding: 16px;
    background: #fff;
    border-top: 1px solid #eee;
    min-height: 100px;

    textarea {
        width: 100%;
        border: 1px solid #e4e7ed;
        border-radius: 8px;
        padding: 12px;
        margin-bottom: 12px;
        resize: none;
        font-size: 14px;
        transition: border-color 0.3s;

        &:focus {
            outline: none;
            border-color: var(--primary-color);
        }

        &:disabled {
            background: #f5f7fa;
            cursor: not-allowed;
        }
    }

    .el-button {
        width: 100%;
        height: 40px;
        font-size: 14px;
        border-radius: 8px;
    }
}

/* 最小化图标样式 */
.minimized-icon {
    position: fixed;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    background: white;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
    cursor: pointer;
    z-index: 1000;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: transform 0.2s;

    img {
        width: 32px;
        height: 32px;
        border-radius: 50%;
    }

    &:hover {
        transform: scale(1.1);
        box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
    }

    &:active {
        transform: scale(0.95);
    }
}
</style> 