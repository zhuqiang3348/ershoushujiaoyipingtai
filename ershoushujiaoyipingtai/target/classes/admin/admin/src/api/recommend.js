import request from '../utils/request'

export function getRecommendations(userId) {
    return request({
        url: `/api/recommend/${userId}`,
        method: 'get'
    })
}