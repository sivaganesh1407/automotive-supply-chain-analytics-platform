import { useState, useEffect } from 'react'

export default function Inventory({ apiBase }) {
  const [inventory, setInventory] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetch(`${apiBase}/inventory`)
      .then(r => r.json())
      .then(setInventory)
      .catch(() => setError('Could not fetch inventory'))
      .finally(() => setLoading(false))
  }, [apiBase])

  if (loading) return <div className="text-slate-500">Loading inventory...</div>
  if (error) return <div className="bg-red-50 text-red-700 p-4 rounded-lg">{error}</div>

  const statusColor = (s) => {
    if (s === 'IN_STOCK') return 'bg-green-100 text-green-800'
    if (s === 'IN_TRANSIT') return 'bg-amber-100 text-amber-800'
    if (s === 'DELIVERED') return 'bg-blue-100 text-blue-800'
    return 'bg-slate-100 text-slate-800'
  }

  return (
    <div>
      <h2 className="text-2xl font-bold text-slate-800 mb-4">Inventory</h2>
      <div className="bg-white rounded-xl shadow overflow-hidden">
        <table className="w-full">
          <thead className="bg-slate-100">
            <tr>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">ID</th>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">Vehicle ID</th>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">Status</th>
              <th className="px-4 py-3 text-left font-semibold text-slate-700">Warehouse</th>
            </tr>
          </thead>
          <tbody>
            {inventory.length === 0 ? (
              <tr><td colSpan={4} className="px-4 py-8 text-center text-slate-500">No inventory. Run ETL to load data.</td></tr>
            ) : (
              inventory.map((inv) => (
                <tr key={inv.id} className="border-t border-slate-100 hover:bg-slate-50">
                  <td className="px-4 py-3">{inv.id}</td>
                  <td className="px-4 py-3">{inv.vehicleId}</td>
                  <td className="px-4 py-3">
                    <span className={`px-2 py-1 rounded text-sm font-medium ${statusColor(inv.status)}`}>
                      {inv.status}
                    </span>
                  </td>
                  <td className="px-4 py-3">{inv.warehouseLocation}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  )
}
