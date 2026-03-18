import { useState } from 'react'
import Dashboard from './components/Dashboard'
import Vehicles from './components/Vehicles'
import Inventory from './components/Inventory'
import Dealers from './components/Dealers'
import ETLPanel from './components/ETLPanel'

const API_BASE = import.meta.env.VITE_API_URL || 'http://localhost:8080'

function App() {
  const [activeTab, setActiveTab] = useState('dashboard')
  const [refreshKey, setRefreshKey] = useState(0)

  const tabs = [
    { id: 'dashboard', label: 'Dashboard', icon: '📊' },
    { id: 'vehicles', label: 'Vehicles', icon: '🚗' },
    { id: 'inventory', label: 'Inventory', icon: '📦' },
    { id: 'dealers', label: 'Dealers', icon: '🏪' },
    { id: 'etl', label: 'Load Data (ETL)', icon: '⚡' },
  ]

  const onETLSuccess = () => setRefreshKey(k => k + 1)

  return (
    <div className="min-h-screen bg-slate-50">
      <header className="bg-slate-900 text-white px-6 py-4 shadow-lg">
        <h1 className="text-xl font-bold">Automotive Supply Chain Analytics</h1>
        <p className="text-slate-400 text-sm mt-1">Production • Inventory • Dealers</p>
      </header>

      <nav className="bg-white border-b border-slate-200 px-6 flex gap-1 overflow-x-auto">
        {tabs.map(({ id, label, icon }) => (
          <button
            key={id}
            onClick={() => setActiveTab(id)}
            className={`px-4 py-3 font-medium text-sm whitespace-nowrap border-b-2 transition-colors ${
              activeTab === id
                ? 'border-blue-500 text-blue-600'
                : 'border-transparent text-slate-600 hover:text-slate-900'
            }`}
          >
            {icon} {label}
          </button>
        ))}
      </nav>

      <main className="p-6 max-w-7xl mx-auto">
        {activeTab === 'dashboard' && <Dashboard apiBase={API_BASE} key={refreshKey} />}
        {activeTab === 'vehicles' && <Vehicles apiBase={API_BASE} key={refreshKey} />}
        {activeTab === 'inventory' && <Inventory apiBase={API_BASE} key={refreshKey} />}
        {activeTab === 'dealers' && <Dealers apiBase={API_BASE} key={refreshKey} />}
        {activeTab === 'etl' && <ETLPanel apiBase={API_BASE} onSuccess={onETLSuccess} />}
      </main>
    </div>
  )
}

export default App
