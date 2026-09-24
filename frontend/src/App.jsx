import { useEffect, useState } from 'react'
import { Activity, Anchor, ArrowUpRight, Boxes, Check, ChevronDown, ChevronRight, CircleAlert, CircleGauge, Compass, Cpu, Edit3, Gauge, LayoutDashboard, LockKeyhole, LogOut, MapPin, Menu, Moon, Plus, Search, Settings, Sun, Trash2, Waves, X, Zap } from 'lucide-react'
import { endpoints, getApiError } from './services/api'
import './App.css'

const navItems = [
  { key: 'dashboard', label: 'Dashboard', icon: LayoutDashboard },
  { key: 'drones', label: 'Drones', icon: Cpu },
  { key: 'sites', label: 'Mining sites', icon: MapPin },
  { key: 'missions', label: 'Missions', icon: Compass },
  { key: 'minerals', label: 'Minerals', icon: Boxes },
]

function App() {
  const [page, setPage] = useState('dashboard')
  const [mobileNav, setMobileNav] = useState(false)
  const [search, setSearch] = useState('')
  const [refreshKey, setRefreshKey] = useState(0)
  const [toast, setToast] = useState(null)
  const [theme, setTheme] = useState(() => localStorage.getItem('abyssal-theme') || 'dark')
  const [authenticated, setAuthenticated] = useState(() => sessionStorage.getItem('abyssal-session') === 'active')
  const [accountOpen, setAccountOpen] = useState(false)
  const [operatorId, setOperatorId] = useState(() => sessionStorage.getItem('abyssal-operator') || 'Operator')
  useEffect(() => { if (!toast) return undefined; const timer = setTimeout(() => setToast(null), 3500); return () => clearTimeout(timer) }, [toast])
  useEffect(() => { localStorage.setItem('abyssal-theme', theme) }, [theme])
  const notify = (message, type = 'success') => setToast({ message, type })
  const refresh = () => setRefreshKey((key) => key + 1)
  const signIn = async (credentials) => {
    const response = await endpoints.login(credentials)
    sessionStorage.setItem('abyssal-session', 'active')
    sessionStorage.setItem('abyssal-operator', response.data.operatorId)
    setOperatorId(response.data.operatorId)
    setAuthenticated(true)
  }
  const signOut = () => { sessionStorage.removeItem('abyssal-session'); sessionStorage.removeItem('abyssal-operator'); setAccountOpen(false); setAuthenticated(false) }
  if (!authenticated) return <LoginPage theme={theme} onLogin={signIn} />
  return <div className={`app-shell ${theme === 'light' ? 'theme-light' : ''}`}>
    <aside className={`sidebar ${mobileNav ? 'sidebar-open' : ''}`}>
      <div className="brand"><div className="brand-mark"><Waves size={20} /></div><div><strong>ABYSSAL</strong><span>operations center</span></div></div>
      <div className="side-label">Fleet command</div>
      <nav>{navItems.map(({ key, label, icon: Icon }) => <button key={key} className={page === key ? 'nav-active' : ''} onClick={() => { setPage(key); setMobileNav(false); setSearch('') }}><Icon size={18} /><span>{label}</span>{page === key && <ChevronRight size={15} />}</button>)}</nav>
      <div className="sidebar-footer"><div className="system-status"><span className="live-dot" />API connection<br /><small>MySQL data link online</small></div></div>
    </aside>
    {mobileNav && <button className="scrim" aria-label="Close navigation" onClick={() => setMobileNav(false)} />}
    <main className="main-content">
      <header className="topbar"><button className="icon-button mobile-menu" onClick={() => setMobileNav(true)} aria-label="Open navigation"><Menu size={21} /></button><div className="breadcrumb"><span>Operations</span><ChevronRight size={14} /><strong>{navItems.find((item) => item.key === page)?.label}</strong></div><div className="top-actions"><div className="signal"><span className="live-dot" /> Systems nominal</div><div className="theme-toggle" role="group" aria-label="Color theme"><button className={theme === 'light' ? 'theme-active' : ''} onClick={() => setTheme('light')} aria-label="Use light theme" title="Light theme"><Sun size={15} /></button><button className={theme === 'dark' ? 'theme-active' : ''} onClick={() => setTheme('dark')} aria-label="Use dark theme" title="Dark theme"><Moon size={15} /></button></div><button className="icon-button" aria-label="Settings"><Settings size={19} /></button><div className="account-menu"><button className="account-trigger" onClick={() => setAccountOpen((open) => !open)} aria-label="Open account menu" aria-expanded={accountOpen}><span className="profile">OC</span><ChevronDown size={14} /></button>{accountOpen && <div className="account-dropdown"><span className="account-label">Signed in as</span><strong>{operatorId}</strong><div className="account-divider" /><button className="logout-button" onClick={signOut}><LogOut size={15} /> Log out</button></div>}</div></div></header>
      <div className="page-wrap">{page === 'dashboard' ? <Dashboard refreshKey={refreshKey} /> : <ResourcePage type={page} search={search} setSearch={setSearch} refresh={refresh} notify={notify} refreshKey={refreshKey} />}</div>
    </main>
    {toast && <div className={`toast ${toast.type}`}><span>{toast.type === 'success' ? <Check size={17} /> : <CircleAlert size={17} />}</span>{toast.message}<button onClick={() => setToast(null)} aria-label="Dismiss"><X size={15} /></button></div>}
  </div>
}

function LoginPage({ theme, onLogin }) {
  const [mode, setMode] = useState('login')
  const [operatorId, setOperatorId] = useState('')
  const [passcode, setPasscode] = useState('')
  const [confirmPasscode, setConfirmPasscode] = useState('')
  const [error, setError] = useState('')
  const submit = async (event) => {
    event.preventDefault()
    setError('')
    if (!operatorId.trim() || !passcode.trim()) return setError('Enter your operator ID and passcode to continue.')
    if (mode === 'register' && passcode !== confirmPasscode) return setError('Passcodes do not match.')
    try {
      if (mode === 'register') {
        await endpoints.register({ operatorId, passcode })
        setMode('login')
        setConfirmPasscode('')
        setError('Registration complete. Sign in with your new operator ID.')
      } else {
        await onLogin({ operatorId, passcode })
      }
    } catch (requestError) {
      const message = getApiError(requestError)
      if (mode === 'login' && message.toLowerCase().includes('not registered')) setMode('register')
      setError(message)
    }
  }
  const isRegistering = mode === 'register'
  return <div className={`login-screen ${theme === 'light' ? 'theme-light' : ''}`}><div className="login-atmosphere" /><div className="login-card"><div className="login-brand"><div className="brand-mark"><Waves size={22} /></div><div><strong>ABYSSAL</strong><span>operations center</span></div></div><div className="login-heading"><span className="eyebrow"><LockKeyhole size={14} /> Secure operator access</span><h1>{isRegistering ? <>Register your<br />operator access.</> : <>Welcome below<br />the surface.</>}</h1><p>{isRegistering ? 'Create an operator ID and passcode to access deep-water operations.' : 'Sign in to monitor fleet readiness and coordinate deep-water operations.'}</p></div><form onSubmit={submit} className="login-form"><label>Operator ID<input value={operatorId} onChange={(event) => setOperatorId(event.target.value)} placeholder="e.g. OC-041" autoComplete="username" /></label><label>Passcode<input type="password" value={passcode} onChange={(event) => setPasscode(event.target.value)} placeholder={isRegistering ? 'At least 6 characters' : 'Enter passcode'} autoComplete={isRegistering ? 'new-password' : 'current-password'} /></label>{isRegistering && <label>Confirm passcode<input type="password" value={confirmPasscode} onChange={(event) => setConfirmPasscode(event.target.value)} placeholder="Repeat passcode" autoComplete="new-password" /></label>}{error && <div className={`login-error ${error.startsWith('Registration complete') ? 'login-success' : ''}`}><CircleAlert size={15} />{error}</div>}<button className="login-button" type="submit">{isRegistering ? 'Create operator account' : 'Enter operations center'} <ArrowUpRight size={17} /></button></form><button className="auth-switch" onClick={() => { setMode(isRegistering ? 'login' : 'register'); setError('') }}>{isRegistering ? 'Already registered? Sign in' : 'Not registered? Create an operator account'}</button><div className="login-footer"><span><span className="live-dot" /> Systems nominal</span><span>Database verified access</span></div></div></div>
}

function useApi(loader, dependencies = []) {
  const [state, setState] = useState({ data: null, loading: true, error: null })
  useEffect(() => { let active = true; setState({ data: null, loading: true, error: null }); loader().then((response) => active && setState({ data: response.data, loading: false, error: null })).catch((error) => active && setState({ data: null, loading: false, error: getApiError(error) })); return () => { active = false } }, dependencies)
  return state
}

function Dashboard({ refreshKey }) {
  const { data, loading, error } = useApi(() => endpoints.dashboard(), [refreshKey]); const stats = data || {}
  if (loading) return <LoadingScreen label="Reading operations data" />; if (error) return <ErrorState message={error} />
  const status = stats.droneStatusBreakdown || {}
  return <><PageIntro eyebrow="Thursday · 24 September 2026" title="Good morning, Operator" description="A clear view of the fleet beneath the surface." icon={<CircleGauge size={17} />} />
    <section className="stat-grid"><Stat label="Total drones" value={stats.totalDrones ?? 0} detail={`${stats.activeDrones ?? 0} active in fleet`} icon={<Cpu />} tone="yellow" /><Stat label="Active missions" value={stats.activeMissions ?? 0} detail="Currently in progress" icon={<Zap />} tone="cyan" /><Stat label="Mining sites" value={stats.totalMiningSites ?? 0} detail="Registered locations" icon={<MapPin />} tone="blue" /><Stat label="Mineral inventory" value={`${Number(stats.totalMineralQuantity || 0).toLocaleString()} t`} detail="Combined recorded yield" icon={<Boxes />} tone="slate" /></section>
    <div className="dashboard-grid"><section className="panel activity-panel"><PanelHeading title="Mission activity" note="Latest dispatches" icon={<Activity size={17} />} /><div className="mission-list">{(stats.recentMissions || []).length ? stats.recentMissions.map((mission) => <MissionRow key={mission.id} mission={mission} />) : <EmptyState label="No mission activity yet" />}</div></section><section className="panel"><PanelHeading title="Drone status" note="Fleet readiness" icon={<Gauge size={17} />} /><div className="status-donut"><div className="donut"><strong>{stats.totalDrones ?? 0}</strong><span>units</span></div><div className="status-legend">{Object.entries(status).length ? Object.entries(status).map(([label, count]) => <div key={label}><i className={`legend-dot ${label.toLowerCase()}`} /><span>{label.replace('_', ' ')}</span><b>{count}</b></div>) : <span className="muted">No drones registered</span>}</div></div></section></div>
    <section className="panel sites-panel"><PanelHeading title="Mining site overview" note="Operational zones" icon={<Anchor size={17} />} /><div className="site-strip">{(stats.miningSitesOverview || []).slice(0, 4).map((site) => <div className="site-mini" key={site.id}><div className="site-map-icon"><MapPin size={17} /></div><div><strong>{site.siteName}</strong><span>{site.location}</span></div><em className={`status-pill ${String(site.status).toLowerCase()}`}>{site.status}</em></div>)}{!(stats.miningSitesOverview || []).length && <EmptyState label="No mining sites registered" />}</div></section>
  </>
}

const resourceConfigs = {
  drones: { title: 'Drone fleet', eyebrow: 'Fleet registry', description: 'Manage vehicles cleared for deep-water operations.', icon: <Cpu size={17} />, endpoint: endpoints.drones, create: endpoints.createDrone, update: endpoints.updateDrone, remove: endpoints.deleteDrone, fields: [{ name: 'droneName', label: 'Drone name' }, { name: 'model', label: 'Model' }, { name: 'operatingDepth', label: 'Operating depth (m)', type: 'number' }, { name: 'status', label: 'Status', type: 'select', options: ['ACTIVE', 'STANDBY', 'MAINTENANCE'] }] },
  sites: { title: 'Mining sites', eyebrow: 'Survey map', description: 'Maintain the locations that shape the current operation.', icon: <MapPin size={17} />, endpoint: endpoints.sites, create: endpoints.createSite, update: endpoints.updateSite, remove: endpoints.deleteSite, fields: [{ name: 'siteName', label: 'Site name' }, { name: 'location', label: 'Location' }, { name: 'depth', label: 'Depth (m)', type: 'number' }, { name: 'status', label: 'Status', type: 'select', options: ['ACTIVE', 'SURVEYING', 'INACTIVE'] }] },
  minerals: { title: 'Mineral inventory', eyebrow: 'Resource ledger', description: 'Track recorded material recovered from each operation.', icon: <Boxes size={17} />, endpoint: endpoints.minerals, create: endpoints.createMineral, update: endpoints.updateMineral, remove: endpoints.deleteMineral, fields: [{ name: 'mineralName', label: 'Mineral name' }, { name: 'type', label: 'Type' }, { name: 'quantity', label: 'Quantity', type: 'number' }, { name: 'unit', label: 'Unit' }] },
  missions: { title: 'Mission control', eyebrow: 'Dispatch board', description: 'Review assignments and keep every deep-water run moving.', icon: <Compass size={17} />, endpoint: endpoints.missions, create: endpoints.createMission, update: endpoints.updateMission, remove: endpoints.deleteMission, fields: [{ name: 'missionName', label: 'Mission name' }, { name: 'droneId', label: 'Assigned drone', type: 'select', source: 'drones' }, { name: 'miningSiteId', label: 'Mining site', type: 'select', source: 'sites' }, { name: 'startDate', label: 'Start date', type: 'date' }, { name: 'status', label: 'Status', type: 'select', options: ['PLANNED', 'IN_PROGRESS', 'COMPLETED'] }] },
}

function ResourcePage({ type, search, setSearch, refresh, notify, refreshKey }) {
  const config = resourceConfigs[type]; const resource = useApi(config.endpoint, [refreshKey]); const drones = useApi(endpoints.drones, type === 'missions' ? [refreshKey] : []); const sites = useApi(endpoints.sites, type === 'missions' ? [refreshKey] : []); const [modal, setModal] = useState(null); const [deleting, setDeleting] = useState(null); const list = resource.data || []; const filtered = list.filter((item) => JSON.stringify(item).toLowerCase().includes(search.toLowerCase()))
  const save = async (payload) => { try { if (modal.item) await config.update(modal.item.id, payload); else await config.create(payload); setModal(null); refresh(); notify('Record saved successfully.') } catch (error) { notify(getApiError(error), 'error') } }
  const remove = async () => { try { await config.remove(deleting.id); setDeleting(null); refresh(); notify('Record deleted successfully.') } catch (error) { notify(getApiError(error), 'error') } }
  if (resource.loading) return <LoadingScreen label={`Loading ${config.title.toLowerCase()}`} />; if (resource.error) return <ErrorState message={resource.error} />
  return <><PageIntro eyebrow={config.eyebrow} title={config.title} description={config.description} icon={config.icon} action={<button className="primary-button" onClick={() => setModal({})}><Plus size={17} /> Add record</button>} /><div className="toolbar"><div className="search-box"><Search size={17} /><input value={search} onChange={(event) => setSearch(event.target.value)} placeholder={`Search ${config.title.toLowerCase()}...`} /></div><span className="result-count">{filtered.length} records</span></div>{type === 'missions' ? <MissionTimeline items={filtered} onEdit={(item) => setModal({ item })} onDelete={setDeleting} /> : <ResourceTable type={type} items={filtered} onEdit={(item) => setModal({ item })} onDelete={setDeleting} />}{modal && <FormModal config={config} item={modal.item} drones={drones.data || []} sites={sites.data || []} onClose={() => setModal(null)} onSave={save} />}{deleting && <ConfirmModal item={deleting} onClose={() => setDeleting(null)} onConfirm={remove} />}</>
}

function ResourceTable({ type, items, onEdit, onDelete }) { const names = { drones: ['Drone', 'Model', 'Depth', 'Status'], sites: ['Site', 'Location', 'Depth', 'Status'], minerals: ['Mineral', 'Type', 'Quantity', 'Unit'] }; return <section className="panel table-panel"><table><thead><tr>{names[type].map((name) => <th key={name}>{name}</th>)}<th /></tr></thead><tbody>{items.map((item) => <tr key={item.id}><td><strong>{item.droneName || item.siteName || item.mineralName}</strong><small>ID-{String(item.id).padStart(4, '0')}</small></td><td>{item.model || item.location || item.type}</td><td>{item.operatingDepth ?? item.depth ?? item.quantity}{type === 'drones' || type === 'sites' ? ' m' : ''}</td><td>{type === 'minerals' ? item.unit : <span className={`status-pill ${String(item.status).toLowerCase()}`}>{String(item.status).replace('_', ' ')}</span>}</td><td><div className="row-actions"><button onClick={() => onEdit(item)} aria-label="Edit"><Edit3 size={16} /></button><button onClick={() => onDelete(item)} aria-label="Delete"><Trash2 size={16} /></button></div></td></tr>)}</tbody></table>{!items.length && <EmptyState label="No records match this search" />}</section> }
function MissionTimeline({ items, onEdit, onDelete }) { return <section className="timeline">{items.map((mission, index) => <article className="mission-card" key={mission.id}><div className="timeline-marker"><span>{String(index + 1).padStart(2, '0')}</span></div><div className="mission-card-body"><div className="mission-card-top"><div><span className="eyebrow">Mission ID-{String(mission.id).padStart(4, '0')}</span><h3>{mission.missionName}</h3></div><span className={`status-pill ${String(mission.status).toLowerCase()}`}>{mission.status?.replace('_', ' ')}</span></div><div className="mission-meta"><span><Cpu size={15} />{mission.drone?.droneName || 'Unassigned drone'}</span><span><MapPin size={15} />{mission.miningSite?.siteName || 'Unassigned site'}</span><span><Activity size={15} />Started {mission.startDate}</span></div><div className="mission-actions"><button onClick={() => onEdit(mission)}><Edit3 size={14} /> Edit mission</button><button onClick={() => onDelete(mission)}><Trash2 size={14} /> Delete</button></div></div></article>)}{!items.length && <section className="panel"><EmptyState label="No missions match this search" /></section>}</section> }

function FormModal({ config, item, drones, sites, onClose, onSave }) { const [form, setForm] = useState(() => Object.fromEntries(config.fields.map((field) => [field.name, item?.[field.name] ?? (field.name === 'status' ? field.options?.[0] : '')]))); const [error, setError] = useState(''); const submit = (event) => { event.preventDefault(); if (config.fields.some((field) => !form[field.name])) return setError('Complete all fields before saving.'); const payload = { ...form }; config.fields.forEach((field) => { if (field.type === 'number' || field.source) payload[field.name] = Number(payload[field.name]) }); onSave(payload) }; return <div className="modal-backdrop"><form className="modal" onSubmit={submit}><div className="modal-head"><div><span className="eyebrow">{item ? 'Edit record' : 'New record'}</span><h2>{item ? 'Update' : 'Add'} record</h2></div><button type="button" className="icon-button" onClick={onClose}><X size={19} /></button></div>{error && <div className="form-error"><CircleAlert size={16} />{error}</div>}<div className="form-grid">{config.fields.map((field) => <label key={field.name}>{field.label}{field.type === 'select' ? <select value={form[field.name]} onChange={(event) => setForm({ ...form, [field.name]: event.target.value })}><option value="">Select...</option>{(field.source === 'drones' ? drones.map((option) => ({ value: option.id, label: option.droneName })) : field.source === 'sites' ? sites.map((option) => ({ value: option.id, label: option.siteName })) : field.options.map((option) => ({ value: option, label: option }))).map((option) => <option key={option.value} value={option.value}>{option.label}</option>)}</select> : <input type={field.type || 'text'} value={form[field.name]} onChange={(event) => setForm({ ...form, [field.name]: event.target.value })} />}</label>)}</div><div className="modal-actions"><button type="button" className="secondary-button" onClick={onClose}>Cancel</button><button className="primary-button" type="submit"><Check size={16} /> Save record</button></div></form></div> }
function ConfirmModal({ item, onClose, onConfirm }) { return <div className="modal-backdrop"><div className="modal confirm-modal"><div className="warning-icon"><Trash2 size={20} /></div><h2>Delete this record?</h2><p>This will permanently remove <strong>{item.droneName || item.siteName || item.mineralName || item.missionName}</strong> from the operations database.</p><div className="modal-actions"><button className="secondary-button" onClick={onClose}>Keep record</button><button className="danger-button" onClick={onConfirm}>Delete</button></div></div></div> }
function PageIntro({ eyebrow, title, description, icon, action }) { return <div className="page-intro"><div><div className="eyebrow intro-eyebrow">{icon}{eyebrow}</div><h1>{title}</h1><p>{description}</p></div>{action}</div> }
function PanelHeading({ title, note, icon }) { return <div className="panel-heading"><div><span>{icon}{note}</span><h2>{title}</h2></div><ArrowUpRight size={17} /></div> }
function Stat({ label, value, detail, icon, tone }) { return <div className={`stat-card ${tone}`}><div className="stat-top"><span>{label}</span><div className="stat-icon">{icon}</div></div><strong>{value}</strong><small><span className="trend">↗</span>{detail}</small></div> }
function MissionRow({ mission }) { return <div className="mission-row"><div className="mission-symbol"><Activity size={17} /></div><div><strong>{mission.missionName}</strong><span>{mission.drone?.droneName || 'Drone pending'} · {mission.miningSite?.siteName || 'Site pending'}</span></div><span className={`status-pill ${String(mission.status).toLowerCase()}`}>{mission.status?.replace('_', ' ')}</span></div> }
function LoadingScreen({ label }) { return <div className="state-screen"><div className="loader"><Waves size={20} /></div><strong>{label}</strong><span>Connecting to the operations database...</span></div> }
function ErrorState({ message }) { return <div className="state-screen error-state"><CircleAlert size={29} /><strong>Operations link unavailable</strong><span>{message}</span><small>Check that MySQL and the Spring Boot API are running.</small></div> }
function EmptyState({ label }) { return <div className="empty-state"><Waves size={22} /><span>{label}</span></div> }

export default App
