import axios from 'axios'

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080/api',
  headers: { 'Content-Type': 'application/json' },
})

export const endpoints = {
  register: (data) => api.post('/auth/register', data),
  login: (data) => api.post('/auth/login', data),
  dashboard: () => api.get('/dashboard/stats'),
  drones: () => api.get('/drones'),
  drone: (id) => api.get(`/drones/${id}`),
  createDrone: (data) => api.post('/drones', data),
  updateDrone: (id, data) => api.put(`/drones/${id}`, data),
  deleteDrone: (id) => api.delete(`/drones/${id}`),
  sites: () => api.get('/mining-sites'),
  createSite: (data) => api.post('/mining-sites', data),
  updateSite: (id, data) => api.put(`/mining-sites/${id}`, data),
  deleteSite: (id) => api.delete(`/mining-sites/${id}`),
  missions: () => api.get('/missions'),
  createMission: (data) => api.post('/missions', data),
  updateMission: (id, data) => api.put(`/missions/${id}`, data),
  deleteMission: (id) => api.delete(`/missions/${id}`),
  minerals: () => api.get('/minerals'),
  createMineral: (data) => api.post('/minerals', data),
  updateMineral: (id, data) => api.put(`/minerals/${id}`, data),
  deleteMineral: (id) => api.delete(`/minerals/${id}`),
}

export function getApiError(error) {
  const data = error.response?.data
  if (data?.message) return data.message
  if (data?.error) return data.error
  if (data && typeof data === 'object') return Object.values(data).join(' ')
  return 'Unable to reach the operations API.'
}
