import { useState, useEffect } from 'react'
import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, PieChart, Pie, Cell } from 'recharts'

const COLORS = ['#3b82f6', '#22c55e', '#f59e0b', '#ef4444']

export default function Dashboard({ apiBase }) {
  const [production, setProduction] = useState(null)
  const [inventory, setInventory] = useState(null)
  const [dealers, setDealers] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    const fetchAll = async () => {
      setLoading(true)
      setError(null)
      try {
        const [p, i, d] = await Promise.all([
          fetch(`${apiBase}/analytics/production`).then(r => r.json()),
          fetch(`${apiBase}/analytics/inventory`).then(r => r.json()),
          fetch(`${apiBase}/analytics/dealers`).then(r => r.json()),
        ])
        setProduction(p)
        setInventory(i)
        setDealers(d)
      } catch (e) {
        setError('Could not connect to API. Is the backend running on port 9090?')
      } finally {
        setLoading(false)
      }
    }
    fetchAll()
  }, [apiBase])

  if (loading) return <div className="text-slate-500">Loading analytics...</div>
  if (error) return <div className="bg-red-50 text-red-700 p-4 rounded-lg">{error}</div>

  const statusData = inventory?.statusBreakdown
    ? Object.entries(inventory.statusBreakdown).map(([name, value]) => ({ name, value }))
    : []
  const regionData = dealers?.regionBreakdown
    ? Object.entries(dealers.regionBreakdown).map(([name, value]) => ({ name, value }))
    : []

  return (
    <div className="space-y-8">
      <h2 className="text-2xl font-bold text-slate-800">Analytics Dashboard</h2>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <StatCard title="Total Vehicles" value={production?.totalVehicles ?? 0} />
        <StatCard title="Defect Rate" value={`${production?.defectRate ?? 0}%`} />
        <StatCard title="Efficiency Score" value={production?.efficiencyScore ?? 0} />
        <StatCard title="Total Inventory" value={inventory?.totalInventory ?? 0} />
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-white rounded-xl shadow p-6">
          <h3 className="font-semibold text-slate-800 mb-4">Inventory by Status</h3>
          {statusData.length > 0 ? (
            <ResponsiveContainer width="100%" height={200}>
              <PieChart>
                <Pie data={statusData} dataKey="value" nameKey="name" cx="50%" cy="50%" outerRadius={70} label>
                  {statusData.map((_, i) => <Cell key={i} fill={COLORS[i % COLORS.length]} />)}
                </Pie>
                <Tooltip />
              </PieChart>
            </ResponsiveContainer>
          ) : (
            <p className="text-slate-500">No inventory data. Run ETL to load sample data.</p>
          )}
        </div>

        <div className="bg-white rounded-xl shadow p-6">
          <h3 className="font-semibold text-slate-800 mb-4">Dealers by Region</h3>
          {regionData.length > 0 ? (
            <ResponsiveContainer width="100%" height={200}>
              <BarChart data={regionData}>
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
                <Bar dataKey="value" fill="#3b82f6" />
              </BarChart>
            </ResponsiveContainer>
          ) : (
            <p className="text-slate-500">No dealer data. Run ETL to load sample data.</p>
          )}
        </div>
      </div>
    </div>
  )
}

function StatCard({ title, value }) {
  return (
    <div className="bg-white rounded-xl shadow p-6">
      <p className="text-slate-500 text-sm font-medium">{title}</p>
      <p className="text-2xl font-bold text-slate-800 mt-1">{value}</p>
    </div>
  )
}
